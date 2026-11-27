/*
 * Copyright (c) 2026 Arka Prava Ghosh
 * SPDX-License-Identifier: Apache-2.0
 */

package com.agentretry.manager.service;

import com.agentretry.manager.domain.TaskEventEntity;
import com.agentretry.manager.domain.TokenUsageEntity;
import org.springframework.stereotype.Component;

/** Tier Core — analytics warehouse not included. */
@Component
public class NoOpAnalyticsSinkPublisher {

    public void publishTokenUsageAfterCommit(TokenUsageEntity usage) {
        // no-op
    }

    public void publishTaskEventAfterCommit(TaskEventEntity event) {
        // no-op
    }
}
