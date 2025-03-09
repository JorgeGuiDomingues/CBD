INSERT INTO users (username, name, email, registration_timestamp) 
VALUES ('johndoe', 'John Doe', 'johndoe@example.com', '2024-11-10 10:00:00');
INSERT INTO users (username, name, email, registration_timestamp) 
VALUES ('janedoe', 'Jane Doe', 'janedoe@example.com', '2024-11-11 11:00:00');
INSERT INTO users (username, name, email, registration_timestamp) 
VALUES ('alice', 'Alice Johnson', 'alice@example.com', '2024-11-12 12:00:00');
INSERT INTO users (username, name, email, registration_timestamp) 
VALUES ('bob', 'Bob Smith', 'bob@example.com', '2024-11-13 13:00:00');
INSERT INTO users (username, name, email, registration_timestamp) 
VALUES ('charlie', 'Charlie Brown', 'charlie@example.com', '2024-11-14 14:00:00');
INSERT INTO users (username, name, email, registration_timestamp) 
VALUES ('david', 'David Lee', 'david@example.com', '2024-11-15 15:00:00');
INSERT INTO users (username, name, email, registration_timestamp) 
VALUES ('eve', 'Eve Adams', 'eve@example.com', '2024-11-16 16:00:00');
INSERT INTO users (username, name, email, registration_timestamp) 
VALUES ('frank', 'Frank White', 'frank@example.com', '2024-11-17 17:00:00');


INSERT INTO videos (video_id, author, video_name, description, tags, upload_timestamp) 
VALUES (11111111-1111-1111-1111-111111111111, 'johndoe', 'Video 1', 'Description 1', ['tag1', 'tag2'], '2024-11-10 10:00:00');
INSERT INTO videos (video_id, author, video_name, description, tags, upload_timestamp) 
VALUES (22222222-2222-2222-2222-222222222222, 'janedoe', 'Video 2', 'Description 2', ['tag3', 'tag4'], '2024-11-11 11:00:00');
INSERT INTO videos (video_id, author, video_name, description, tags, upload_timestamp) 
VALUES (33333333-3333-3333-3333-333333333333, 'alice', 'Video 3', 'Description 3', ['tag1', 'tag5'], '2024-11-12 12:00:00');
INSERT INTO videos (video_id, author, video_name, description, tags, upload_timestamp) 
VALUES (44444444-4444-4444-4444-444444444444, 'bob', 'Video 4', 'Description 4', ['tag6', 'tag2'], '2024-11-13 13:00:00');
INSERT INTO videos (video_id, author, video_name, description, tags, upload_timestamp) 
VALUES (55555555-5555-5555-5555-555555555555, 'charlie', 'Video 5', 'Description 5', ['tag7', 'tag8'], '2024-11-14 14:00:00');
INSERT INTO videos (video_id, author, video_name, description, tags, upload_timestamp) 
VALUES (66666666-6666-6666-6666-666666666666, 'david', 'Video 6', 'Description 6', ['tag1', 'tag9'], '2024-11-15 15:00:00');
INSERT INTO videos (video_id, author, video_name, description, tags, upload_timestamp) 
VALUES (77777777-7777-7777-7777-777777777777, 'eve', 'Video 7', 'Description 7', ['tag10', 'tag2'], '2024-11-16 16:00:00');
INSERT INTO videos (video_id, author, video_name, description, tags, upload_timestamp) 
VALUES (88888888-8888-8888-8888-888888888888, 'frank', 'Video 8', 'Description 8', ['tag3', 'tag11'], '2024-11-17 17:00:00');


INSERT INTO comments (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (11111111-1111-1111-1111-111111111111, now(), 'johndoe', 'Great video!', '2024-11-10 10:10:00');
INSERT INTO comments (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (22222222-2222-2222-2222-222222222222, now(), 'janedoe', 'Very informative.', '2024-11-11 11:15:00');
INSERT INTO comments (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (33333333-3333-3333-3333-333333333333, now(), 'alice', 'Loved it!', '2024-11-12 12:20:00');
INSERT INTO comments (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (44444444-4444-4444-4444-444444444444, now(), 'bob', 'Good content.', '2024-11-13 13:25:00');
INSERT INTO comments (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (55555555-5555-5555-5555-555555555555, now(), 'charlie', 'Nice work.', '2024-11-14 14:30:00');
INSERT INTO comments (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (66666666-6666-6666-6666-666666666666, now(), 'david', 'Helpful video.', '2024-11-15 15:35:00');
INSERT INTO comments (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (77777777-7777-7777-7777-777777777777, now(), 'eve', 'Thank you!', '2024-11-16 16:40:00');
INSERT INTO comments (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (88888888-8888-8888-8888-888888888888, now(), 'frank', 'Well done!', '2024-11-17 17:45:00');


INSERT INTO video_followers (video_id, username, follow_timestamp) 
VALUES (11111111-1111-1111-1111-111111111111, 'johndoe', '2024-11-10 10:00:00');
INSERT INTO video_followers (video_id, username, follow_timestamp) 
VALUES (22222222-2222-2222-2222-222222222222, 'janedoe', '2024-11-11 11:00:00');
INSERT INTO video_followers (video_id, username, follow_timestamp) 
VALUES (33333333-3333-3333-3333-333333333333, 'alice', '2024-11-12 12:00:00');
INSERT INTO video_followers (video_id, username, follow_timestamp) 
VALUES (44444444-4444-4444-4444-444444444444, 'bob', '2024-11-13 13:00:00');
INSERT INTO video_followers (video_id, username, follow_timestamp) 
VALUES (55555555-5555-5555-5555-555555555555, 'charlie', '2024-11-14 14:00:00');
INSERT INTO video_followers (video_id, username, follow_timestamp) 
VALUES (66666666-6666-6666-6666-666666666666, 'david', '2024-11-15 15:00:00');
INSERT INTO video_followers (video_id, username, follow_timestamp) 
VALUES (77777777-7777-7777-7777-777777777777, 'eve', '2024-11-16 16:00:00');
INSERT INTO video_followers (video_id, username, follow_timestamp) 
VALUES (88888888-8888-8888-8888-888888888888, 'frank', '2024-11-17 17:00:00');



INSERT INTO video_ratings (video_id, total_rating, rating_count) 
VALUES (11111111-1111-1111-1111-111111111111, 20, 5); -- Média: 4.0
INSERT INTO video_ratings (video_id, total_rating, rating_count) 
VALUES (22222222-2222-2222-2222-222222222222, 15, 4); -- Média: 3.75
INSERT INTO video_ratings (video_id, total_rating, rating_count) 
VALUES (33333333-3333-3333-3333-333333333333, 25, 6); -- Média: 4.16
INSERT INTO video_ratings (video_id, total_rating, rating_count) 
VALUES (44444444-4444-4444-4444-444444444444, 30, 7); -- Média: 4.28
INSERT INTO video_ratings (video_id, total_rating, rating_count) 
VALUES (55555555-5555-5555-5555-555555555555, 18, 5); -- Média: 3.6
INSERT INTO video_ratings (video_id, total_rating, rating_count) 
VALUES (66666666-6666-6666-6666-666666666666, 22, 6); -- Média: 3.66
INSERT INTO video_ratings (video_id, total_rating, rating_count) 
VALUES (77777777-7777-7777-7777-777777777777, 16, 4); -- Média: 4.0
INSERT INTO video_ratings (video_id, total_rating, rating_count) 
VALUES (88888888-8888-8888-8888-888888888888, 12, 3); -- Média: 4.0



INSERT INTO comments_by_video (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (55555555-5555-5555-5555-555555555555, now(), 'charlie', 'Very detailed content.', '2024-11-14 14:30:00');

INSERT INTO comments_by_video (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (66666666-6666-6666-6666-666666666666, now(), 'david', 'Helpful and clear.', '2024-11-15 15:35:00');

INSERT INTO comments_by_video (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (77777777-7777-7777-7777-777777777777, now(), 'eve', 'Good job!', '2024-11-16 16:40:00');

INSERT INTO comments_by_video (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (88888888-8888-8888-8888-888888888888, now(), 'frank', 'Well structured.', '2024-11-17 17:45:00');

INSERT INTO comments_by_video (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (11111111-1111-1111-1111-111111111111, now(), 'alice', 'Incredible work!', '2024-11-10 11:00:00');

INSERT INTO comments_by_video (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (22222222-2222-2222-2222-222222222222, now(), 'bob', 'Keep it up!', '2024-11-11 12:00:00');

INSERT INTO comments_by_video (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (33333333-3333-3333-3333-333333333333, now(), 'charlie', 'I loved the examples!', '2024-11-12 13:00:00');

INSERT INTO comments_by_video (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (44444444-4444-4444-4444-444444444444, now(), 'david', 'Super clear explanation.', '2024-11-13 14:00:00');

INSERT INTO comments_by_video (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (55555555-5555-5555-5555-555555555555, now(), 'eve', 'Very detailed walkthrough!', '2024-11-14 15:00:00');

INSERT INTO comments_by_video (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (66666666-6666-6666-6666-666666666666, now(), 'frank', 'This was really helpful.', '2024-11-15 16:00:00');

INSERT INTO comments_by_video (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (77777777-7777-7777-7777-777777777777, now(), 'johndoe', 'Amazing effort!', '2024-11-16 17:00:00');

INSERT INTO comments_by_video (video_id, comment_id, username, comment, comment_timestamp) 
VALUES (88888888-8888-8888-8888-888888888888, now(), 'janedoe', 'Very engaging content.', '2024-11-17 18:00:00');



INSERT INTO comments_by_user (username, comment_id, video_id, comment, comment_timestamp) 
VALUES ('johndoe', now(), 11111111-1111-1111-1111-111111111111, 'Great video!', '2024-11-10 10:10:00');

INSERT INTO comments_by_user (username, comment_id, video_id, comment, comment_timestamp) 
VALUES ('janedoe', now(), 22222222-2222-2222-2222-222222222222, 'Very informative.', '2024-11-11 11:15:00');

INSERT INTO comments_by_user (username, comment_id, video_id, comment, comment_timestamp) 
VALUES ('alice', now(), 33333333-3333-3333-3333-333333333333, 'Loved it!', '2024-11-12 12:20:00');

INSERT INTO comments_by_user (username, comment_id, video_id, comment, comment_timestamp) 
VALUES ('bob', now(), 44444444-4444-4444-4444-444444444444, 'Good content.', '2024-11-13 13:25:00');

INSERT INTO comments_by_user (username, comment_id, video_id, comment, comment_timestamp) 
VALUES ('charlie', now(), 55555555-5555-5555-5555-555555555555, 'Nice work.', '2024-11-14 14:30:00');

INSERT INTO comments_by_user (username, comment_id, video_id, comment, comment_timestamp) 
VALUES ('david', now(), 66666666-6666-6666-6666-666666666666, 'Helpful video.', '2024-11-15 15:35:00');

INSERT INTO comments_by_user (username, comment_id, video_id, comment, comment_timestamp) 
VALUES ('eve', now(), 77777777-7777-7777-7777-777777777777, 'Thank you!', '2024-11-16 16:40:00');

INSERT INTO comments_by_user (username, comment_id, video_id, comment, comment_timestamp) 
VALUES ('frank', now(), 88888888-8888-8888-8888-888888888888, 'Well done!', '2024-11-17 17:45:00');



INSERT INTO videos_by_author (author, video_id, video_name, description, upload_timestamp) 
VALUES ('johndoe', 11111111-1111-1111-1111-111111111111, 'Video 1', 'Description 1', '2024-11-10 10:00:00');

INSERT INTO videos_by_author (author, video_id, video_name, description, upload_timestamp) 
VALUES ('janedoe', 22222222-2222-2222-2222-222222222222, 'Video 2', 'Description 2', '2024-11-11 11:00:00');

INSERT INTO videos_by_author (author, video_id, video_name, description, upload_timestamp) 
VALUES ('alice', 33333333-3333-3333-3333-333333333333, 'Video 3', 'Description 3', '2024-11-12 12:00:00');

INSERT INTO videos_by_author (author, video_id, video_name, description, upload_timestamp) 
VALUES ('bob', 44444444-4444-4444-4444-444444444444, 'Video 4', 'Description 4', '2024-11-13 13:00:00');

INSERT INTO videos_by_author (author, video_id, video_name, description, upload_timestamp) 
VALUES ('charlie', 55555555-5555-5555-5555-555555555555, 'Video 5', 'Description 5', '2024-11-14 14:00:00');

INSERT INTO videos_by_author (author, video_id, video_name, description, upload_timestamp) 
VALUES ('david', 66666666-6666-6666-6666-666666666666, 'Video 6', 'Description 6', '2024-11-15 15:00:00');

INSERT INTO videos_by_author (author, video_id, video_name, description, upload_timestamp) 
VALUES ('eve', 77777777-7777-7777-7777-777777777777, 'Video 7', 'Description 7', '2024-11-16 16:00:00');

INSERT INTO videos_by_author (author, video_id, video_name, description, upload_timestamp) 
VALUES ('frank', 88888888-8888-8888-8888-888888888888, 'Video 8', 'Description 8', '2024-11-17 17:00:00');



INSERT INTO video_events (username, event_timestamp, video_id, event_type, video_second) 
VALUES ('johndoe', '2024-11-10 10:01:00', 11111111-1111-1111-1111-111111111111, 'play', 0);

INSERT INTO video_events (username, event_timestamp, video_id, event_type, video_second) 
VALUES ('janedoe', '2024-11-11 11:02:00', 22222222-2222-2222-2222-222222222222, 'pause', 120);

INSERT INTO video_events (username, event_timestamp, video_id, event_type, video_second) 
VALUES ('alice', '2024-11-12 12:03:00', 33333333-3333-3333-3333-333333333333, 'stop', 300);

INSERT INTO video_events (username, event_timestamp, video_id, event_type, video_second) 
VALUES ('bob', '2024-11-13 13:04:00', 44444444-4444-4444-4444-444444444444, 'play', 60);

INSERT INTO video_events (username, event_timestamp, video_id, event_type, video_second) 
VALUES ('charlie', '2024-11-14 14:05:00', 55555555-5555-5555-5555-555555555555, 'pause', 180);

INSERT INTO video_events (username, event_timestamp, video_id, event_type, video_second) 
VALUES ('david', '2024-11-15 15:06:00', 66666666-6666-6666-6666-666666666666, 'stop', 240);

INSERT INTO video_events (username, event_timestamp, video_id, event_type, video_second) 
VALUES ('eve', '2024-11-16 16:07:00', 77777777-7777-7777-7777-777777777777, 'play', 0);

INSERT INTO video_events (username, event_timestamp, video_id, event_type, video_second) 
VALUES ('frank', '2024-11-17 17:08:00', 88888888-8888-8888-8888-888888888888, 'pause', 90);



INSERT INTO videos_by_tag (tag, video_id, video_name) VALUES ('Aveiro', 11111111-1111-1111-1111-111111111111, 'Video 1');
INSERT INTO videos_by_tag (tag, video_id, video_name) VALUES ('Travel', 11111111-1111-1111-1111-111111111111, 'Video 1');


INSERT INTO comments_followers (username, video_id, comment_id, comment, comment_timestamp)
VALUES ('johndoe', 11111111-1111-1111-1111-111111111111, now(), 'Great video!', '2024-11-10 11:00:00');

INSERT INTO comments_followers (username, video_id, comment_id, comment, comment_timestamp)
VALUES ('johndoe', 11111111-1111-1111-1111-111111111111, now(), 'Very informative.', '2024-11-10 11:05:00');


INSERT INTO tags_count (tag, video_count) VALUES ('Travel', 1) IF NOT EXISTS;
INSERT INTO tags_count (tag, video_count) VALUES ('Adventure', 1) IF NOT EXISTS;

UPDATE tags_count SET video_count = video_count + 1 WHERE tag = 'Travel';
UPDATE tags_count SET video_count = video_count + 1 WHERE tag = 'Adventure';


UPDATE video_follower_counts SET follower_count = follower_count + 1 WHERE video_id = 11111111-1111-1111-1111-111111111111;
UPDATE video_follower_counts SET follower_count = follower_count + 1 WHERE video_id = 22222222-2222-2222-2222-222222222222;
UPDATE video_follower_counts SET follower_count = follower_count + 1 WHERE video_id = 22222222-2222-2222-2222-222222222222;


INSERT INTO recent_videos_by_user (username, video_id, last_watch_timestamp) 
VALUES ('johndoe', 11111111-1111-1111-1111-111111111111, '2024-11-10 10:01:00');
INSERT INTO recent_videos_by_user (username, video_id, last_watch_timestamp) 
VALUES ('alice', 33333333-3333-3333-3333-333333333333, '2024-11-12 12:03:00');
INSERT INTO recent_videos_by_user (username, video_id, last_watch_timestamp)
VALUES ('johndoe', 22222222-2222-2222-2222-222222222222, '2024-11-11 11:02:00');


INSERT INTO common_tags_videos (tag1, tag2, video_id) 
VALUES ('tag1', 'tag2', 11111111-1111-1111-1111-111111111111);
INSERT INTO common_tags_videos (tag1, tag2, video_id) 
VALUES ('tag1', 'tag5', 33333333-3333-3333-3333-333333333333);
INSERT INTO common_tags_videos (tag1, tag2, video_id)
VALUES ('tag1', 'tag9', 66666666-6666-6666-6666-666666666666);