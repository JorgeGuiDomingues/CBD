package pt.ua.cbd.lab3.ex3;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;

import java.net.InetSocketAddress;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class MainB {
    public static void main(String[] args) {
        try (CqlSession session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .withLocalDatacenter("datacenter1")
                .build()) {

            // Especificar o keyspace a ser usado
            session.execute("USE exercisetwo;");

            // Query 2: Lista das tags de determinado vídeo
            System.out.println("Query 2: Lista das tags de determinado vídeo");
            String queryTags = "SELECT tags FROM videos WHERE author = ? AND upload_timestamp = ? AND video_id = ?";
            PreparedStatement stmtTags = session.prepare(queryTags);

            String author = "johndoe";
            UUID videoId = UUID.fromString("11111111-1111-1111-1111-111111111111");
            String timestampString = "2024-11-10 10:00:00";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Instant uploadTimestamp = LocalDateTime.parse(timestampString, formatter).toInstant(ZoneOffset.UTC);

            BoundStatement boundTags = stmtTags.bind(author, uploadTimestamp, videoId);
            ResultSet rsTags = session.execute(boundTags);

            Row tagsRow = rsTags.one();
            if (tagsRow != null) {
                List<String> tags = tagsRow.getList("tags", String.class);
                System.out.println("Tags: " + tags);
            } else {
                System.out.println("Nenhum resultado encontrado.");
            }

            // Query 4b: Todos os eventos de determinado utilizador
            System.out.println("\nQuery 4b: Todos os eventos de determinado utilizador");
            String queryEventsByUser = "SELECT event_type, event_timestamp, video_second FROM video_events WHERE username = ?";
            PreparedStatement stmtEventsByUser = session.prepare(queryEventsByUser);

            String username = "johndoe";
            BoundStatement boundEventsByUser = stmtEventsByUser.bind(username);
            ResultSet rsEventsByUser = session.execute(boundEventsByUser);

            for (Row row : rsEventsByUser) {
                System.out.println("Tipo de evento: " + row.getString("event_type")
                        + ", Timestamp: " + row.getInstant("event_timestamp")
                        + ", Segundo do vídeo: " + row.getInt("video_second"));
            }

            // Query 8: Todos os comentários dos vídeos que um utilizador está a seguir
            System.out.println("\nQuery 8: Comentários dos vídeos que um utilizador está a seguir");
            String queryCommentsByFollowing = "SELECT comment, comment_timestamp FROM comments_followers WHERE username = ? AND video_id = ?";
            PreparedStatement stmtCommentsByFollowing = session.prepare(queryCommentsByFollowing);

            BoundStatement boundCommentsByFollowing = stmtCommentsByFollowing.bind(username, videoId);
            ResultSet rsCommentsByFollowing = session.execute(boundCommentsByFollowing);

            for (Row row : rsCommentsByFollowing) {
                System.out.println("Comentário: " + row.getString("comment")
                        + ", Timestamp: " + row.getInstant("comment_timestamp"));
            }

            // Query 10: Todos os vídeos com a ordem de apresentação
            System.out.println("\nQuery 10: Todos os vídeos e a ordem de apresentação");
            String queryAllVideos = "SELECT author, video_id, video_name FROM videos_by_author";
            PreparedStatement stmtAllVideos = session.prepare(queryAllVideos);

            ResultSet rsAllVideos = session.execute(stmtAllVideos.bind());

            for (Row row : rsAllVideos) {
                System.out.println("Autor: " + row.getString("author")
                        + ", Video ID: " + row.getUuid("video_id")
                        + ", Nome do Vídeo: " + row.getString("video_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
