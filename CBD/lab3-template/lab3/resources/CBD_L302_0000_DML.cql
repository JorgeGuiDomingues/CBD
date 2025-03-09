SELECT JSON * FROM users;

 [json]
----------------------------------------------------------------------------------------------------------------------------------------
      {"username": "johndoe", "email": "johndoe@example.com", "name": "John Doe", "registration_timestamp": "2024-11-10 10:00:00.000Z"}
             {"username": "bob", "email": "bob@example.com", "name": "Bob Smith", "registration_timestamp": "2024-11-13 13:00:00.000Z"}
             {"username": "eve", "email": "eve@example.com", "name": "Eve Adams", "registration_timestamp": "2024-11-16 16:00:00.000Z"}
       {"username": "frank", "email": "frank@example.com", "name": "Frank White", "registration_timestamp": "2024-11-17 17:00:00.000Z"}
      {"username": "janedoe", "email": "janedoe@example.com", "name": "Jane Doe", "registration_timestamp": "2024-11-11 11:00:00.000Z"}
 {"username": "charlie", "email": "charlie@example.com", "name": "Charlie Brown", "registration_timestamp": "2024-11-14 14:00:00.000Z"}
     {"username": "alice", "email": "alice@example.com", "name": "Alice Johnson", "registration_timestamp": "2024-11-12 12:00:00.000Z"}
         {"username": "david", "email": "david@example.com", "name": "David Lee", "registration_timestamp": "2024-11-15 15:00:00.000Z"}

(8 rows)


SELECT JSON * FROM videos;

 [json]
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 {"author": "johndoe", "upload_timestamp": "2024-11-10 10:00:00.000Z", "video_id": "11111111-1111-1111-1111-111111111111", "description": "Description 1", "tags": ["tag1", "tag2"], "video_name": "Video 1"}
     {"author": "bob", "upload_timestamp": "2024-11-13 13:00:00.000Z", "video_id": "44444444-4444-4444-4444-444444444444", "description": "Description 4", "tags": ["tag6", "tag2"], "video_name": "Video 4"}
    {"author": "eve", "upload_timestamp": "2024-11-16 16:00:00.000Z", "video_id": "77777777-7777-7777-7777-777777777777", "description": "Description 7", "tags": ["tag10", "tag2"], "video_name": "Video 7"}
  {"author": "frank", "upload_timestamp": "2024-11-17 17:00:00.000Z", "video_id": "88888888-8888-8888-8888-888888888888", "description": "Description 8", "tags": ["tag3", "tag11"], "video_name": "Video 8"}
 {"author": "janedoe", "upload_timestamp": "2024-11-11 11:00:00.000Z", "video_id": "22222222-2222-2222-2222-222222222222", "description": "Description 2", "tags": ["tag3", "tag4"], "video_name": "Video 2"}
 {"author": "charlie", "upload_timestamp": "2024-11-14 14:00:00.000Z", "video_id": "55555555-5555-5555-5555-555555555555", "description": "Description 5", "tags": ["tag7", "tag8"], "video_name": "Video 5"}
   {"author": "alice", "upload_timestamp": "2024-11-12 12:00:00.000Z", "video_id": "33333333-3333-3333-3333-333333333333", "description": "Description 3", "tags": ["tag1", "tag5"], "video_name": "Video 3"}
   {"author": "david", "upload_timestamp": "2024-11-15 15:00:00.000Z", "video_id": "66666666-6666-6666-6666-666666666666", "description": "Description 6", "tags": ["tag1", "tag9"], "video_name": "Video 6"}

(8 rows)


SELECT JSON * FROM comments;

 [json]
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            {"video_id": "77777777-7777-7777-7777-777777777777", "comment_id": "183c0030-a7fb-11ef-8228-290c0261acc3", "comment": "Thank you!", "comment_timestamp": "2024-11-16 16:40:00.000Z", "username": "eve"}
      {"video_id": "66666666-6666-6666-6666-666666666666", "comment_id": "183aeec0-a7fb-11ef-8228-290c0261acc3", "comment": "Helpful video.", "comment_timestamp": "2024-11-15 15:35:00.000Z", "username": "david"}
 {"video_id": "22222222-2222-2222-2222-222222222222", "comment_id": "18387dc0-a7fb-11ef-8228-290c0261acc3", "comment": "Very informative.", "comment_timestamp": "2024-11-11 11:15:00.000Z", "username": "janedoe"}
         {"video_id": "44444444-4444-4444-4444-444444444444", "comment_id": "18396820-a7fb-11ef-8228-290c0261acc3", "comment": "Good content.", "comment_timestamp": "2024-11-13 13:25:00.000Z", "username": "bob"}
        {"video_id": "55555555-5555-5555-5555-555555555555", "comment_id": "183a5280-a7fb-11ef-8228-290c0261acc3", "comment": "Nice work.", "comment_timestamp": "2024-11-14 14:30:00.000Z", "username": "charlie"}
          {"video_id": "88888888-8888-8888-8888-888888888888", "comment_id": "183c7560-a7fb-11ef-8228-290c0261acc3", "comment": "Well done!", "comment_timestamp": "2024-11-17 17:45:00.000Z", "username": "frank"}
      {"video_id": "11111111-1111-1111-1111-111111111111", "comment_id": "1837e180-a7fb-11ef-8228-290c0261acc3", "comment": "Great video!", "comment_timestamp": "2024-11-10 10:10:00.000Z", "username": "johndoe"}
           {"video_id": "33333333-3333-3333-3333-333333333333", "comment_id": "1838f2f0-a7fb-11ef-8228-290c0261acc3", "comment": "Loved it!", "comment_timestamp": "2024-11-12 12:20:00.000Z", "username": "alice"}

(8 rows)


SELECT JSON * FROM video_followers;

 [json]
-----------------------------------------------------------------------------------------------------------------------------
     {"video_id": "77777777-7777-7777-7777-777777777777", "username": "eve", "follow_timestamp": "2024-11-16 16:00:00.000Z"}
   {"video_id": "66666666-6666-6666-6666-666666666666", "username": "david", "follow_timestamp": "2024-11-15 15:00:00.000Z"}
 {"video_id": "22222222-2222-2222-2222-222222222222", "username": "janedoe", "follow_timestamp": "2024-11-11 11:00:00.000Z"}
     {"video_id": "44444444-4444-4444-4444-444444444444", "username": "bob", "follow_timestamp": "2024-11-13 13:00:00.000Z"}
 {"video_id": "55555555-5555-5555-5555-555555555555", "username": "charlie", "follow_timestamp": "2024-11-14 14:00:00.000Z"}
   {"video_id": "88888888-8888-8888-8888-888888888888", "username": "frank", "follow_timestamp": "2024-11-17 17:00:00.000Z"}
 {"video_id": "11111111-1111-1111-1111-111111111111", "username": "johndoe", "follow_timestamp": "2024-11-10 10:00:00.000Z"}
   {"video_id": "33333333-3333-3333-3333-333333333333", "username": "alice", "follow_timestamp": "2024-11-12 12:00:00.000Z"}

(8 rows)


SELECT JSON * FROM video_events;

 [json]
------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 {"video_id": "22222222-2222-2222-2222-222222222222", "username": "janedoe", "event_timestamp": "2024-11-11 11:02:00.000Z", "event_type": "pause", "video_second": 120}
    {"video_id": "66666666-6666-6666-6666-666666666666", "username": "david", "event_timestamp": "2024-11-15 15:06:00.000Z", "event_type": "stop", "video_second": 240}
        {"video_id": "77777777-7777-7777-7777-777777777777", "username": "eve", "event_timestamp": "2024-11-16 16:07:00.000Z", "event_type": "play", "video_second": 0}
    {"video_id": "11111111-1111-1111-1111-111111111111", "username": "johndoe", "event_timestamp": "2024-11-10 10:01:00.000Z", "event_type": "play", "video_second": 0}
       {"video_id": "44444444-4444-4444-4444-444444444444", "username": "bob", "event_timestamp": "2024-11-13 13:04:00.000Z", "event_type": "play", "video_second": 60}
 {"video_id": "55555555-5555-5555-5555-555555555555", "username": "charlie", "event_timestamp": "2024-11-14 14:05:00.000Z", "event_type": "pause", "video_second": 180}
    {"video_id": "88888888-8888-8888-8888-888888888888", "username": "frank", "event_timestamp": "2024-11-17 17:08:00.000Z", "event_type": "pause", "video_second": 90}
    {"video_id": "33333333-3333-3333-3333-333333333333", "username": "alice", "event_timestamp": "2024-11-12 12:03:00.000Z", "event_type": "stop", "video_second": 300}

(8 rows)


SELECT JSON * FROM video_ratings;

 [json]
---------------------------------------------------------------------------------------------
 {"video_id": "77777777-7777-7777-7777-777777777777", "rating_count": 4, "total_rating": 16}
 {"video_id": "66666666-6666-6666-6666-666666666666", "rating_count": 6, "total_rating": 22}
 {"video_id": "22222222-2222-2222-2222-222222222222", "rating_count": 4, "total_rating": 15}
 {"video_id": "44444444-4444-4444-4444-444444444444", "rating_count": 7, "total_rating": 30}
 {"video_id": "55555555-5555-5555-5555-555555555555", "rating_count": 5, "total_rating": 18}
 {"video_id": "88888888-8888-8888-8888-888888888888", "rating_count": 3, "total_rating": 12}
 {"video_id": "11111111-1111-1111-1111-111111111111", "rating_count": 5, "total_rating": 20}
 {"video_id": "33333333-3333-3333-3333-333333333333", "rating_count": 6, "total_rating": 25}

(8 rows)