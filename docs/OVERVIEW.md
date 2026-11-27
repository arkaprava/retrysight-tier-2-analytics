# RetrySight Analytics — capabilities

**Installation tier:** `TIER_2_ANALYTICS`  
**Spring profile:** `tier-analytics`

## Adds on top of Core

| Area | Capabilities |
|------|--------------|
| **Cost** | Token cost estimates, model pricing, contract cost *review* |
| **Reports** | CSV/JSON/PDF exports |
| **Collectors** | Kiro CLI/IDE, Warp, Copilot, Windsurf, Continue, Cline, Aider, OpenCode |
| **Git context** | Capture (not query) |
| **Archive** | Local filesystem archive |
| **Evaluations** | Eval definitions, runs, annotation queue, playground |
| **Rollups** | Analytics rollups for long ranges |

## UI routes added

`/cost`, `/retries`, `/reports`, `/evaluations`, `/playground`

## Still disabled

MCP, SSO tools API, contract cost *apply*, ClickHouse, traces/patterns/experiments, push ingest, object archive.

## Database

**PostgreSQL 16** only (`agent_retry_analytics`). No ClickHouse at this tier.
