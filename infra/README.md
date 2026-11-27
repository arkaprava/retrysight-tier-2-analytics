# Infrastructure (Docker)

Use the **repository root** for deployment — see [`docs/DEPLOYMENT.md`](../docs/DEPLOYMENT.md) and [`docs/RELEASE.md`](../docs/RELEASE.md).

Dockerfiles: `infra/manager`, `infra/frontend`, `infra/agent`. PostgreSQL: `docker/postgres/`.

## Images (tier-analytics)

| Service | Default tag |
|---------|-------------|
| postgres | `retrysight/tier-analytics-postgres:local` |
| manager | `retrysight/tier-analytics-manager:local` |
| frontend | `retrysight/tier-analytics-frontend:local` |
| agent | `retrysight/tier-analytics-agent:local` |

Default dashboard port **8082**, manager **28473**, Postgres host port **5433**.

Legacy `infra/docker-compose.yml` and `infra/scripts/*` may exist from monorepo extraction — prefer root [`docker-compose.yml`](../docker-compose.yml) and [`scripts/`](../scripts/).
