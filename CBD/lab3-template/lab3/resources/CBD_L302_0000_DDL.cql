CREATE KEYSPACE exercisetwo
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE exercisetwo ;

CREATE TABLE users (
    username TEXT PRIMARY KEY,
    name TEXT,
    email TEXT,
    registration_timestamp TIMESTAMP
);

CREATE TABLE videos (
    author TEXT,
    video_id UUID,
    video_name TEXT,
    description TEXT,
    tags LIST<TEXT>,
    upload_timestamp TIMESTAMP,
    PRIMARY KEY (author, upload_timestamp, video_id)
) WITH CLUSTERING ORDER BY (upload_timestamp DESC);

CREATE TABLE comments (
    video_id UUID,
    comment_id TIMEUUID,
    username TEXT,
    comment TEXT,
    comment_timestamp TIMESTAMP,
    PRIMARY KEY (video_id, comment_id)
) WITH CLUSTERING ORDER BY (comment_id DESC);

CREATE TABLE video_followers (
    video_id UUID,
    username TEXT,
    follow_timestamp TIMESTAMP,
    PRIMARY KEY (video_id, username)
);

CREATE TABLE video_events (
    video_id UUID,
    username TEXT,
    event_type TEXT,
    event_timestamp TIMESTAMP,
    video_second INT,
    PRIMARY KEY (username, video_id, event_timestamp)
);

CREATE TABLE video_ratings (
    video_id UUID PRIMARY KEY,
    total_rating INT,
    rating_count INT
);

CREATE TABLE videos_by_author (
    author TEXT,
    video_id UUID,
    video_name TEXT,
    description TEXT,
    upload_timestamp TIMESTAMP,
    PRIMARY KEY (author, upload_timestamp)
) WITH CLUSTERING ORDER BY (upload_timestamp DESC);

CREATE TABLE comments_by_user (
    username TEXT,
    comment_id TIMEUUID,
    video_id UUID,
    comment TEXT,
    comment_timestamp TIMESTAMP,
    PRIMARY KEY (username, comment_timestamp)
) WITH CLUSTERING ORDER BY (comment_timestamp DESC);

CREATE TABLE comments_by_video (
    video_id UUID,
    comment_id TIMEUUID,
    username TEXT,
    comment TEXT,
    comment_timestamp TIMESTAMP,
    PRIMARY KEY (video_id, comment_timestamp)
) WITH CLUSTERING ORDER BY (comment_timestamp DESC);

CREATE TABLE videos_by_tag (
    tag TEXT,
    video_id UUID,
    video_name TEXT,
    PRIMARY KEY (tag, video_id)
);

CREATE TABLE comments_followers (
    username TEXT,
    video_id UUID,
    comment_id TIMEUUID,
    comment TEXT,
    comment_timestamp TIMESTAMP,
    PRIMARY KEY ((username, video_id), comment_timestamp)
) WITH CLUSTERING ORDER BY (comment_timestamp DESC);

CREATE TABLE tags_count (
    tag TEXT PRIMARY KEY,
    video_count COUNTER
);

CREATE TABLE recent_videos_by_user (
    username TEXT,
    video_id UUID,
    last_watch_timestamp TIMESTAMP,
    PRIMARY KEY (username, last_watch_timestamp)
) WITH CLUSTERING ORDER BY (last_watch_timestamp DESC);

CREATE TABLE common_tags_videos (
    tag1 TEXT,
    tag2 TEXT,
    video_id UUID,
    PRIMARY KEY ((tag1, tag2), video_id)
);
