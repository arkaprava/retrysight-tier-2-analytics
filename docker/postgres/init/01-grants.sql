GRANT ALL PRIVILEGES ON DATABASE agent_retry_analytics TO retrysight_analytics;
GRANT ALL PRIVILEGES ON SCHEMA public TO retrysight_analytics;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO retrysight_analytics;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO retrysight_analytics;

-- Analytics tier: enable statement stats for operator dashboards
CREATE EXTENSION IF NOT EXISTS pg_stat_statements;
