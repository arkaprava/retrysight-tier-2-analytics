# RetrySight Analytics

[![License: Apache-2.0](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](LICENSE)
[![Release](https://img.shields.io/github/v/release/arkaprava/retrysight-tier-2-analytics?sort=semver)](https://github.com/arkaprava/retrysight-tier-2-analytics/releases)

**See every retry. Know every dollar.** RetrySight is self-hosted observability for AI coding agents
(Cursor, Claude Code, Codex and more): tasks, retries, tokens and cost across your whole fleet.

Analytics tier: everything in Core **plus** cost analytics, reports, extended collectors, git context capture, evaluations, and local archive.

Installation tier `TIER_2_ANALYTICS` · Spring profile `docker,tier-analytics` · [retrysight.com](https://retrysight.com)

## The RetrySight lineup

Each tier is a standalone, self-contained repository. Higher tiers include everything below them.

| Tier | Repository | Adds |
|------|------------|------|
| Core | [`retrysight-tier-1-core`](https://github.com/arkaprava/retrysight-tier-1-core) | Ingest, dashboard, tasks, live feed, collector registry, retention |
| **Analytics** | [`retrysight-tier-2-analytics`](https://github.com/arkaprava/retrysight-tier-2-analytics) | + cost analytics, reports, evaluations, extended collectors, git context |
| Integration | [`retrysight-tier-3-integration`](https://github.com/arkaprava/retrysight-tier-3-integration) | + MCP server, SSO tools API, ClickHouse analytics, traces, patterns |
| Cloud | [`retrysight-tier-4-cloud`](https://github.com/arkaprava/retrysight-tier-4-cloud) | + push ingest, S3/MinIO archive, cold restore, git enterprise privacy |

Looking for a single-user desktop console? See [RetrySight Lite](https://github.com/arkaprava/retrysight-lite).

## Install

### Docker Compose (prebuilt images)

```bash
curl -fsSL https://app.retrysight.com/enterprise/tier-analytics/latest/docker-compose.yml -o docker-compose.yml
docker compose up -d
```

Dashboard: http://localhost:8082 · Manager API: http://localhost:28473

Multi-arch images (amd64, arm64) are published to GHCR (`docker pull ghcr.io/arkaprava/retrysight-tier-2-analytics/manager:1.0.0`), and as
`docker save` tarballs on the [releases page](https://github.com/arkaprava/retrysight-tier-2-analytics/releases) and at
`https://app.retrysight.com/enterprise/tier-analytics/latest/` for air-gapped installs (`docker load -i retrysight-manager-1.0.0-linux-amd64.tar.gz`; arm64 tarballs too).

### Kubernetes (Helm)

```bash
curl -fsSLO https://app.retrysight.com/enterprise/tier-analytics/latest/retrysight-1.0.0.tgz
helm upgrade --install retrysight ./retrysight-1.0.0.tgz -n retrysight --create-namespace
```

The chart generates the database password, JWT secret, admin password and agent API key on first
install and keeps them across upgrades. `helm status retrysight -n retrysight` prints how to read them.
See [`docs/RELEASE.md`](docs/RELEASE.md) for values and ingress options.

### Collector agent

Native installers (Windows `.msi`, macOS `.pkg`, Linux `.deb`) and the agent jar are attached to each
[release](https://github.com/arkaprava/retrysight-tier-2-analytics/releases).

## Build from source

### Components

| Component | Image | Notes |
|-----------|-------|-------|
| PostgreSQL 16 | `retrysight/tier-analytics-postgres:local` | OLTP — `agent_retry_analytics` |
| Manager | `retrysight/tier-analytics-manager:local` | Profile `docker,tier-analytics` |
| Dashboard | `retrysight/tier-analytics-frontend:local` | Cost, reports, evaluations nav |
| Collector | `retrysight/tier-analytics-agent:local` | Core + Kiro, Warp, Copilot, … |

### Quick start

From the **repository root**:

```bash
cp .env.example .env
./scripts/build.sh
./scripts/up-with-agent.sh
# or: ./scripts/up.sh --profile agent
```

| Service | URL |
|---------|-----|
| Dashboard | http://localhost:8082 |
| Manager API | http://localhost:28473 |
| PostgreSQL | localhost:5433 |

Login: `secadmin@local` / `changeme` — compose **dev defaults** from `.env.example`. Change them (and set `SECURITY_ALLOW_DEFAULT_SECRETS=false`) before exposing the stack to anyone else.

### Docs

| Doc | Purpose |
|-----|---------|
| [`docs/OVERVIEW.md`](docs/OVERVIEW.md) | Capabilities |
| [`docs/DEPLOYMENT.md`](docs/DEPLOYMENT.md) | Production checklist |
| [`docs/RELEASE.md`](docs/RELEASE.md) | CI, Helm, native agent installers |

### Fleet agent

```bash
./scripts/retrysight-agent install
./scripts/retrysight-agent discover
./scripts/retrysight-agent run
```

Validate: `./scripts/validation/agent-edge-cases.sh`

## Repository layout

| Path | Purpose |
|------|---------|
| `manager/manager-app` | Spring Boot manager (GraphQL + ingest REST) |
| `agent/agent-app` | Collector agent (Windows, macOS, Linux) |
| `ui/manager-dashboard` | React dashboard |
| `shared/agent-contract` | Shared ingest DTOs |
| `helm/retrysight/` | Kubernetes Helm chart |
| `infra/`, `docker/` | Dockerfiles, PostgreSQL image and init scripts |
| `config/agent/` | Collector + fleet config examples |
| `scripts/` | Build, run, packaging and validation helpers |
| `docs/` | Documentation |

## Contributing

Issues and pull requests are welcome — see [CONTRIBUTING.md](CONTRIBUTING.md).
Please report security issues privately as described in [SECURITY.md](SECURITY.md).

## License

Licensed under the [Apache License, Version 2.0](LICENSE). Copyright © 2026 Arka Prava Ghosh.
