/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.battery.serializer.disk;

import com.facebook.battery.metrics.disk.DiskMetrics;
import com.facebook.battery.serializer.core.SystemMetricsSerializer;
import com.facebook.infer.annotation.Nullsafe;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DiskMetricsSerializer extends SystemMetricsSerializer<DiskMetrics> {

  private static final long serialVersionUID = -3940877017738808059L;

  @Override
  public long getTag() {
    return serialVersionUID;
  }

  @Override
  public void serializeContents(DiskMetrics metrics, DataOutput output) throws IOException {
    output.writeLong(metrics.rcharBytes);
    output.writeLong(metrics.wcharBytes);
    output.writeLong(metrics.syscrCount);
    output.writeLong(metrics.syscwCount);
    output.writeLong(metrics.readBytes);
    output.writeLong(metrics.writeBytes);
    output.writeLong(metrics.cancelledWriteBytes);
    output.writeLong(metrics.majorFaults);
    output.writeLong(metrics.blkIoTicks);
  }

  @Override
  public boolean deserializeContents(DiskMetrics metrics, DataInput input) throws IOException {
    metrics.rcharBytes = input.readLong();
    metrics.wcharBytes = input.readLong();
    metrics.syscrCount = input.readLong();
    metrics.syscwCount = input.readLong();
    metrics.readBytes = input.readLong();
    metrics.writeBytes = input.readLong();
    metrics.cancelledWriteBytes = input.readLong();
    metrics.majorFaults = input.readLong();
    metrics.blkIoTicks = input.readLong();
    return true;
  }
}
