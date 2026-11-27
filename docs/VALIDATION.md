# RetrySight validation guide

Executable validation for RetrySight Analytics (`TIER_2_ANALYTICS`): smoke tests, agent hardening, security, and capacity probes.

## Quick start

```bash
./scripts/validation/smoke.sh
./scripts/validation/agent-edge-cases.sh
./scripts/validation/run-all.sh    # when manager is up
```

| Variable | Default | Notes |
|----------|---------|-------|
| `MANAGER_URL` | `http://localhost:28473` | This tier's manager port |
| `UI_URL` | `http://localhost:8082` | Docker dashboard; `5173` for Vite dev only |
| `APM_EMAIL` / `APM_PASSWORD` | `secadmin@local` / `changeme` | |
| `AGENT_API_KEY` | `dev-agent-key` | |

## Agent hardening

```bash
./scripts/validation/agent-edge-cases.sh   # 5 checks — run after agent changes
```

## CI

**Release** workflow (`.github/workflows/release.yml`) on every PR — see [RELEASE.md](./RELEASE.md).

```bash
mvn -pl shared/agent-contract,manager/manager-app,agent/agent-app -am test
cd ui/manager-dashboard && npm run build
```

## Manual smoke (after `./scripts/up-with-agent.sh`)

1. http://localhost:8082 — login  
2. Dashboard + `/cost` load for tier-analytics  
3. `/agents` shows collectors  
4. `./scripts/validation/smoke.sh` with `MANAGER_URL=http://localhost:28473`

## Related docs

- [SECURITY.md](./SECURITY.md)
- [CAPACITY.md](./CAPACITY.md)
- [OVERVIEW.md](./OVERVIEW.md)
