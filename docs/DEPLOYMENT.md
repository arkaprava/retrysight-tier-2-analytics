# RetrySight Analytics — deployment

Default ports differ from Core so both tiers can run on one machine:

| Variable | Default |
|----------|---------|
| `POSTGRES_PORT` | 5433 |
| `MANAGER_PORT` | 28473 |
| `FRONTEND_PORT` | 8082 |
| `SPRING_PROFILES_ACTIVE` | `docker,tier-analytics` |

## Local stack

From the **repository root**:

```bash
cp .env.example .env
./scripts/validate-env.sh
./scripts/build.sh
./scripts/up-with-agent.sh
```

## Collector config

[`config/agent/config.yaml`](../config/agent/config.yaml) enables Tier-2 collectors. Fleet template: [`config/agent/fleet-config.example.yaml`](../config/agent/fleet-config.example.yaml).

Install on developer machines:

```bash
./scripts/retrysight-agent install
./scripts/retrysight-agent run
```

Native installers: `./scripts/package-agent.sh` — see [RELEASE.md](./RELEASE.md).

| Package | Auto-start after reboot / login |
|---------|----------------------------------|
| macOS `.pkg` | LaunchAgent `com.retrysight.agent` (`RunAtLoad` + `KeepAlive`) |
| Linux `.deb` | User systemd unit `retrysight-agent.service` (`systemctl --global enable`) |
| Windows `.msi` | Ships `register-autostart.ps1` (run once, or use install-agent-service.ps1) → Scheduled task `RetrySightAgent` (`AtLogOn`) |

Manual / jar installs: `./infra/scripts/install-agent-service.sh` (macOS/Linux) or `infra/windows/install-agent-service.ps1`.

## Production

- Set `SPRING_PROFILES_ACTIVE=docker,tier-analytics,prod`
- Contract cost **apply** remains disabled until Integration tier
- External PostgreSQL: [BYO_RDBMS.md](./BYO_RDBMS.md)
- Release images / Helm: [RELEASE.md](./RELEASE.md)

## Release CI

GitHub Actions **Release** workflow builds Docker images, Helm chart, and `.deb`/`.msi`/`.pkg` agent artifacts on every PR. See [RELEASE.md](./RELEASE.md).
