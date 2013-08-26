/*
 * Copyright 2010-2013 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ning.billing.osgi.bundles.analytics.dao.model;

import java.util.UUID;

import javax.annotation.Nullable;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.ning.billing.account.api.Account;
import com.ning.billing.entitlement.api.BlockingState;
import com.ning.billing.util.audit.AuditLog;

public class BusinessOverdueStatusModelDao extends BusinessModelDaoBase {

    private static final String OVERDUE_STATUS_TABLE_NAME = "bos";
    private Long blockingStateRecordId;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;

    public BusinessOverdueStatusModelDao() { /* When reading from the database */ }

    public BusinessOverdueStatusModelDao(final Long blockingStateRecordId,
                                         final String status,
                                         final LocalDate startDate,
                                         final LocalDate endDate,
                                         final DateTime createdDate,
                                         final String createdBy,
                                         final String createdReasonCode,
                                         final String createdComments,
                                         final UUID accountId,
                                         final String accountName,
                                         final String accountExternalKey,
                                         final Long accountRecordId,
                                         final Long tenantRecordId,
                                         @Nullable final ReportGroup reportGroup) {
        super(createdDate,
              createdBy,
              createdReasonCode,
              createdComments,
              accountId,
              accountName,
              accountExternalKey,
              accountRecordId,
              tenantRecordId,
              reportGroup);
        this.blockingStateRecordId = blockingStateRecordId;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public BusinessOverdueStatusModelDao(final Account account,
                                         final Long accountRecordId,
                                         final String stateName,
                                         final LocalDate startDate,
                                         final Long blockingStateRecordId,
                                         final LocalDate endDate,
                                         @Nullable final AuditLog creationAuditLog,
                                         final Long tenantRecordId,
                                         @Nullable final ReportGroup reportGroup) {
        this(blockingStateRecordId,
             stateName,
             startDate,
             endDate,
             creationAuditLog != null ? creationAuditLog.getCreatedDate() : null,
             creationAuditLog != null ? creationAuditLog.getUserName() : null,
             creationAuditLog != null ? creationAuditLog.getReasonCode() : null,
             creationAuditLog != null ? creationAuditLog.getComment() : null,
             account.getId(),
             account.getName(),
             account.getExternalKey(),
             accountRecordId,
             tenantRecordId,
             reportGroup);
    }

    @Override
    public String getTableName() {
        return OVERDUE_STATUS_TABLE_NAME;
    }

    public Long getBlockingStateRecordId() {
        return blockingStateRecordId;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BusinessOverdueStatusModelDao");
        sb.append("{blockingStateRecordId=").append(blockingStateRecordId);
        sb.append(", status='").append(status).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        final BusinessOverdueStatusModelDao that = (BusinessOverdueStatusModelDao) o;

        if (blockingStateRecordId != null ? !blockingStateRecordId.equals(that.blockingStateRecordId) : that.blockingStateRecordId != null) {
            return false;
        }
        if (endDate != null ? (endDate.compareTo(that.endDate) != 0) : that.endDate != null) {
            return false;
        }
        if (startDate != null ? (startDate.compareTo(that.startDate) != 0) : that.startDate != null) {
            return false;
        }
        if (status != null ? !status.equals(that.status) : that.status != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (blockingStateRecordId != null ? blockingStateRecordId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
