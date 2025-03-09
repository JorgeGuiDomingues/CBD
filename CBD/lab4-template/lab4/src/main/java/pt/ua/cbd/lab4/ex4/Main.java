package pt.ua.cbd.lab4.ex4;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.neo4j.driver.Result;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main implements AutoCloseable {

    private final Driver driver;

    public Main(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    private void loadData(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                String tourneyId = values[0];
                String tourneyName = values[1];
                String surface = values[2];
                String tourneyDate = values[5];
                String matchNum = values[6];
                String winnerId = values[7];
                String winnerName = values[10];
                String loserId = values[15];
                String loserName = values[18];
                String score = values[23];

                try (Session session = driver.session()) {
                    session.executeWrite(tx -> {
                        // Criar Tournament
                        tx.run("MERGE (t:Tournament {id: $tourneyId}) " +
                                "ON CREATE SET t.name = $tourneyName, t.surface = $surface, t.date = $tourneyDate",
                                Values.parameters("tourneyId", tourneyId, "tourneyName", tourneyName,
                                        "surface", surface, "tourneyDate", tourneyDate));

                        tx.run("MERGE (w:Player {id: $winnerId}) " +
                                "ON CREATE SET w.name = $winnerName",
                                Values.parameters("winnerId", winnerId, "winnerName", winnerName));
                        tx.run("MERGE (l:Player {id: $loserId}) " +
                                "ON CREATE SET l.name = $loserName",
                                Values.parameters("loserId", loserId, "loserName", loserName));

                        tx.run("MERGE (m:Match {id: $matchNum, score: $score})",
                                Values.parameters("matchNum", matchNum, "score", score));

                        tx.run("MATCH (w:Player {id: $winnerId}), (m:Match {id: $matchNum}) " +
                                "CREATE (w)-[:VENCEU]->(m)",
                                Values.parameters("winnerId", winnerId, "matchNum", matchNum));
                        tx.run("MATCH (l:Player {id: $loserId}), (m:Match {id: $matchNum}) " +
                                "CREATE (l)-[:PERDEU]->(m)",
                                Values.parameters("loserId", loserId, "matchNum", matchNum));
                        tx.run("MATCH (t:Tournament {id: $tourneyId}), (m:Match {id: $matchNum}) " +
                                "CREATE (m)-[:JOGADO_EM]->(t)",
                                Values.parameters("tourneyId", tourneyId, "matchNum", matchNum));

                        return null;
                    });
                }
            }
        }
    }

    private void executeQueries(String outputPath) throws IOException {
        String[] queries = {
                "MATCH (t:Tournament) RETURN t.name AS Tournament, t.surface AS Surface;",
                "MATCH (t:Tournament)<-[:JOGADO_EM]-(m:Match) RETURN t.surface AS Surface, COUNT(m) AS Matches;",
                "MATCH (p:Player)-[:VENCEU]->() RETURN p.name AS Player, COUNT(*) AS Wins ORDER BY Wins DESC LIMIT 5;",
                "MATCH (p:Player {id: '208119'})-[:VENCEU|PERDEU]->(m:Match) RETURN p.name AS Player, m.id AS MatchID, m.score AS Score;",
                "MATCH (p:Player)-[:VENCEU|PERDEU]->(m:Match) RETURN COUNT(DISTINCT p) AS TotalPlayers;",
                "MATCH (t:Tournament)<-[:JOGADO_EM]-(m:Match) RETURN t.name AS Tournament, COUNT(m) AS Matches ORDER BY Matches DESC LIMIT 5;",
                "MATCH (m:Match) RETURN m.score AS Score, COUNT(*) AS Frequency ORDER BY Frequency DESC;",
                "MATCH (p:Player)-[:VENCEU|PERDEU]->(m:Match)-[:JOGADO_EM]->(t:Tournament {id: '2024-M-ITF-ARG-2024-001'}) RETURN DISTINCT p.name AS Player;",
                "MATCH (p:Player)-[:VENCEU]->(m:Match) WITH p, COUNT(m) AS Wins WHERE Wins > 10 RETURN p.name AS Player, Wins ORDER BY Wins DESC;",
                "MATCH (t:Tournament) RETURN t.name AS Tournament, t.date AS Date ORDER BY t.date DESC LIMIT 50;",
        };

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
                Session session = driver.session()) {
            for (int i = 0; i < queries.length; i++) {
                String query = queries[i];
                writer.write("Query " + (i + 1) + ": " + query + "\n");

                Result result = session.run(query);
                while (result.hasNext()) {
                    writer.write(result.next().toString() + "\n");
                }

                writer.write("\n");
            }
        }
    }

    @Override
    public void close() {
        driver.close();
    }

    public static void main(String[] args) throws IOException {
        try (var main = new Main("bolt://localhost:7687", "neo4j", "password")) {
            String csvFile = "resources/atp_matches_futures_2024.csv";
            main.loadData(csvFile);
            System.out.println("Dados carregados com sucesso!");

            String outputPath = "CBD_L44c_output.txt";
            main.executeQueries(outputPath);
            System.out.println("Queries executadas com sucesso! Consulte o ficheiro: " + outputPath);
        }
    }
}
