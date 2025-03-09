package pt.ua.cbd.lab3.ex3;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;

import java.net.InetSocketAddress;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

public class MainA {
    public static void main(String[] args) {
        try (CqlSession session = CqlSession.builder() // Connect to the local Cassandra cluster
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042)) // default port
                .withLocalDatacenter("datacenter1") // datacenter1 is the default datacenter name
                .build()) { // Create a session object

            session.execute("USE exercisetwo;");

            String author = "john_doe";
            UUID videoId = UUID.randomUUID();
            String videoName = "Java Basics";
            String description = "Learn Java fundamentals";
            Instant uploadTimestamp = Instant.now();

            String insertVideoQuery = "INSERT INTO videos (author, video_id, video_name, description, tags, upload_timestamp) "
                    + "VALUES (?, ?, ?, ?, ?, ?)"; // CQL insert query
            PreparedStatement insertVideoStmt = session.prepare(insertVideoQuery); // Prepare the query

            session.execute(insertVideoStmt.bind(
                    author,
                    videoId,
                    videoName,
                    description,
                    Arrays.asList("java", "tutorial"),
                    uploadTimestamp));

            System.out.println(
                    "Vídeo inserido com ID: " + videoId + ", " + author + ", " + videoName + ", " + description + ", "
                            + "tags: " + Arrays.asList("java", "tutorial") + ", " + uploadTimestamp);

            String updateVideoQuery = "UPDATE videos SET description = ? WHERE author = ? AND upload_timestamp = ? AND video_id = ?"; // CQL
                                                                                                                                      // update
                                                                                                                                      // query
            PreparedStatement updateVideoStmt = session.prepare(updateVideoQuery);

            session.execute(updateVideoStmt.bind(
                    "Java fundamentals step-by-step", // new description
                    author,
                    uploadTimestamp,
                    videoId));
            System.out.println("Descrição do vídeo atualizada.");

            String searchVideosQuery = "SELECT video_name, description, tags FROM videos WHERE author = ?"; // CQL
                                                                                                            // select
                                                                                                            // query
            PreparedStatement searchVideosStmt = session.prepare(searchVideosQuery);

            ResultSet rs = session.execute(searchVideosStmt.bind(author)); // Execute the query
            for (Row row : rs) {
                System.out.println("Vídeo encontrado: " + row.getString("video_name") + ", "
                        + row.getString("description") + ", " + row.getList("tags", String.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
