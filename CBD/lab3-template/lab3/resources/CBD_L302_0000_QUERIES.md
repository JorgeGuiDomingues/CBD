## // 7. Permitir a pesquisa de todos os videos de determinado autor;
```sql
SELECT video_id, video_name, description, upload_timestamp 
    FROM videos_by_author 
    WHERE author = 'johndoe';
```

```bash
 video_id                             | video_name | description   | upload_timestamp
--------------------------------------+------------+---------------+---------------------------------
 11111111-1111-1111-1111-111111111111 |    Video 1 | Description 1 | 2024-11-10 10:00:00.000000+0000

(1 rows)
```
### Justificação:
A tabela videos_by_author é particionada por author, o que permite uma pesquisa eficiente. A chave de clustering upload_timestamp está ordenada de forma decrescente para facilitar a recuperação dos vídeos mais recentes primeiro. Isso é ideal para listar os vídeos de um autor em ordem cronológica inversa.

---

## // 8. Permitir a pesquisa de comentarios por utilizador, ordenado inversamente pela data;
```sql
SELECT video_id, comment, comment_timestamp 
    FROM comments_by_user 
    WHERE username = 'johndoe';
```

```bash
 video_id                             | comment      | comment_timestamp
--------------------------------------+--------------+---------------------------------
 11111111-1111-1111-1111-111111111111 | Great video! | 2024-11-10 10:10:00.000000+0000

(1 rows)
```
### Justificação:
A tabela comments_by_user utiliza username como chave de partição e comment_timestamp como chave de clustering, ordenada de forma decrescente. Essa organização é eficiente para recuperar rapidamente os comentários mais recentes de um utilizador.

---

## // 9. Permitir a pesquisa de comentarios por videos, ordenado inversamente pela data;
```sql
SELECT username, comment, comment_timestamp 
    FROM comments_by_video 
    WHERE video_id = 11111111-1111-1111-1111-111111111111;
```

```bash
 username | comment          | comment_timestamp
----------+------------------+---------------------------------
    alice | Incredible work! | 2024-11-10 11:00:00.000000+0000

(1 rows)
```
### Justificação:
A tabela comments_by_video utiliza video_id como chave de partição e comment_timestamp como chave de clustering, também ordenada de forma decrescente. Isso permite recuperar os comentários mais recentes de um vídeo de forma eficiente.

---

## // 10. Permitir a pesquisa do rating medio de um video e quantas vezes foi votado;
```sql
SELECT total_rating, rating_count, total_rating / rating_count AS average_rating 
    FROM video_ratings 
    WHERE video_id = 11111111-1111-1111-1111-111111111111;
```

```bash
 total_rating | rating_count | average_rating
--------------+--------------+----------------
           20 |            5 |              4

(1 rows)
```
### Justificação:
A tabela video_ratings é estruturada para armazenar o total_rating e o rating_count de cada vídeo, o que permite calcular o rating médio diretamente na query. Isso é eficiente, pois evita cálculos externos e usa Cassandra como repositório de agregação.

---

## // 1. Os ultimos 3 comentarios introduzidos para um video;
```sql
SELECT comment, username, comment_timestamp 
    FROM comments_by_video 
    WHERE video_id = 11111111-1111-1111-1111-111111111111 
    LIMIT 3;
```

```bash
 comment          | username | comment_timestamp
------------------+----------+---------------------------------
 Incredible work! |    alice | 2024-11-10 11:00:00.000000+0000

(1 rows)
```
### Justificação:
A ordenação decrescente por comment_timestamp na tabela comments_by_video permite limitar a query aos 3 comentários mais recentes.

---

## // 2. Lista das tags de determinado video; 
```sql
SELECT tags 
    FROM videos 
    WHERE author = 'johndoe' 
    AND upload_timestamp = '2024-11-10 10:00:00' 
    AND video_id = 11111111-1111-1111-1111-111111111111;
```

```bash
 tags
------------------
 ['tag1', 'tag2']

(1 rows)
```
### Justificação:
As tags são armazenadas como uma lista na tabela videos, tornando a recuperação direta e eficiente.

---

## // 3. Todos os videos com a tag Aveiro;
```sql
SELECT tag, video_id, video_name FROM videos_by_tag WHERE tag = 'Aveiro';
```

```bash
 tag    | video_id                             | video_name
--------+--------------------------------------+------------
 Aveiro | 11111111-1111-1111-1111-111111111111 |    Video 1

(1 rows)
```
### Justificação:
A tabela videos_by_tag utiliza tag como chave de partição, o que possibilita a recuperação eficiente de todos os vídeos associados a uma tag específica.

---

## // 4.a. Os últimos 5 eventos de determinado vídeo realizados por um utilizador;
```sql
SELECT event_type, video_second, video_id, event_timestamp 
    FROM video_events 
    WHERE username = 'johndoe' AND video_id = 11111111-1111-1111-1111-111111111111
    LIMIT 5;
```

```bash
 event_type | video_second | video_id                             | event_timestamp
------------+--------------+--------------------------------------+---------------------------------
       play |            0 | 11111111-1111-1111-1111-111111111111 | 2024-11-10 10:01:00.000000+0000

(1 rows)
```
### Justificação:
A tabela video_events está particionada por username e utiliza (video_id, event_timestamp) como chave de clustering. Isso permite filtrar eventos por utilizador e vídeo, ordenando-os por timestamp. O uso de LIMIT 5 retorna os eventos mais recentes.

---

## // 4.b. Todos os eventos de determinado utilizador;
```sql
SELECT event_type, event_timestamp, video_second 
    FROM video_events 
    WHERE username = 'johndoe';
```

```bash
 event_type | event_timestamp                 | video_second
------------+---------------------------------+--------------
       play | 2024-11-10 10:01:00.000000+0000 |            0

(1 rows)
```
### Justificação:
A chave primária da tabela video_events é (username, video_id, event_timestamp), permitindo listar todos os eventos relacionados a um utilizador.

---

## // 4.c. Todos os eventos de determinado utilizador to tipo "pause"
```sql
CREATE INDEX ON video_events (event_type);
```

```sql
SELECT event_type, event_timestamp, video_second 
    FROM video_events 
    WHERE username = 'frank' 
    AND event_type = 'pause';
```

```bash
 event_type | event_timestamp                 | video_second
------------+---------------------------------+--------------
      pause | 2024-11-17 17:08:00.000000+0000 |           90

(1 rows)
```
### Justificação:
Criar um índice em event_type permite filtrar eventos específicos, como "pause".
O índice auxilia na execução da query, que combina filtros por utilizador e tipo de evento.

---

## // 5. Videos partilhados por determinado utilizador (maria1987, por exemplo) num determinado periodo de tempo (Agosto de 2017, por exemplo);
```bash
SELECT video_id, video_name, description, upload_timestamp 
               ... FROM videos_by_author 
               ... WHERE author = 'maria1987' 
               ...   AND upload_timestamp >= '2017-08-01' 
               ...   AND upload_timestamp <= '2017-08-31';

 video_id | video_name | description | upload_timestamp
----------+------------+-------------+------------------

(0 rows)
```
### Justificação:
A tabela videos_by_author está ordenada por upload_timestamp dentro de cada autor, permitindo a filtragem por intervalo de tempo de forma eficiente.

---

## // 6. Os ultimos 10 videos, ordenado inversamente pela data da partilhada;
### Justificação:
Cassandra não suporta consultas globais que não utilizem a chave de partição. A tabela videos ou videos_by_author exige que uma chave de partição (como author) seja especificada para filtrar os resultados.

---

## // 7. Todos os seguidores (followers) de determinado video;
```sql
SELECT username 
    FROM video_followers 
    WHERE video_id = 11111111-1111-1111-1111-111111111111;
```

```bash
 username
----------
  johndoe

(1 rows)
```
### Justificação:
A tabela video_followers está particionada por video_id, permitindo recuperar eficientemente todos os seguidores de um vídeo.

---

## // 8. Todos os comentarios (dos videos) que determinado utilizador esta a seguir (following);
```sql
SELECT comment, comment_timestamp 
    FROM comments_followers 
    WHERE username = 'johndoe' 
    AND video_id = 11111111-1111-1111-1111-111111111111;
```

```bash
 comment           | comment_timestamp
-------------------+---------------------------------
 Very informative. | 2024-11-10 11:05:00.000000+0000
      Great video! | 2024-11-10 11:00:00.000000+0000

(2 rows)
```
### Justificação:
A tabela comments_followers está particionada por (username, video_id) e ordenada por comment_timestamp, permitindo listar comentários de vídeos seguidos por um utilizador.

---

## // 9. Os 5 videos com maior rating;
### Justificação:
Cassandra não suporta ordenação global sem chave de partição. A tabela video_ratings exige que video_id seja especificado como chave de partição.

---

## // 10. Uma query que retorne todos os videos e que mostre claramente a forma pela qual estao ordenados;
```sql
SELECT author, video_id, video_name FROM videos_by_author ;
```

```bash
 author  | video_id                             | video_name
---------+--------------------------------------+------------
 johndoe | 11111111-1111-1111-1111-111111111111 |    Video 1
     bob | 44444444-4444-4444-4444-444444444444 |    Video 4
     eve | 77777777-7777-7777-7777-777777777777 |    Video 7
   frank | 88888888-8888-8888-8888-888888888888 |    Video 8
 janedoe | 22222222-2222-2222-2222-222222222222 |    Video 2
 charlie | 55555555-5555-5555-5555-555555555555 |    Video 5
   alice | 33333333-3333-3333-3333-333333333333 |    Video 3
   david | 66666666-6666-6666-6666-666666666666 |    Video 6

(8 rows)
```

### Justificação:
A tabela videos_by_author está ordenada por upload_timestamp em ordem decrescente dentro de cada autor. A consulta demonstra a ordenação natural da tabela.

---

## // 11. Lista com as Tags existentes e o numero de videos catalogados com cada uma delas;
```sql
SELECT * FROM tags_count ;
```

```bash
 tag       | video_count
-----------+-------------
    Travel |           2
 Adventure |           1

(2 rows)

```
### Justificação:
A tabela tags_count foi criada como uma tabela de contadores, permitindo armazenar o número de vídeos associados a cada tag.

---

## // 12. Quantos utilizadors seguem (follow) cada video;
```sql
SELECT video_id, follower_count FROM video_follower_counts;
```

```bash
 video_id                             | follower_count
--------------------------------------+----------------
 22222222-2222-2222-2222-222222222222 |              2
 11111111-1111-1111-1111-111111111111 |              1

(2 rows)
```
### Justificação:
A tabela video_follower_counts armazena diretamente a contagem de seguidores para cada vídeo, tornando a consulta eficiente.

---

## // 13. Quais são os vídeos mais recentes assistidos por cada utilizador;
```sql
SELECT video_id, last_watch_timestamp FROM recent_videos_by_user WHERE username = 'johndoe';
```

```bash
 video_id                             | last_watch_timestamp
--------------------------------------+---------------------------------
 22222222-2222-2222-2222-222222222222 | 2024-11-11 11:02:00.000000+0000
 11111111-1111-1111-1111-111111111111 | 2024-11-10 10:01:00.000000+0000

(2 rows)
```
### Justificação:
A tabela recent_videos_by_user está particionada por username e ordenada por last_watch_timestamp em ordem decrescente, permitindo obter os vídeos mais recentes assistidos.

---

## // 14. Quais tags foram associadas recentemente a novos vídeos;
```sql
SELECT video_id
FROM common_tags_videos
WHERE tag1 = 'tag1' AND tag2 = 'tag2';
```

```bash
 video_id
--------------------------------------
 11111111-1111-1111-1111-111111111111

(1 rows)
```
### Justificação:
A tabela common_tags_videos está particionada por (tag1, tag2), permitindo listar vídeos associados a combinações específicas de tags.
