/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.battery.metrics.cpu;

import androidx.annotation.Nullable;
import com.facebook.battery.metrics.core.SystemMetrics;
import com.facebook.infer.annotation.Nullsafe;

/** Information about system and user cpu metrics: maintains a double per type of CPU time. */
@Nullsafe(Nullsafe.Mode.LOCAL)
public class CpuMetrics extends SystemMetrics<CpuMetrics> {

  public double userTimeS;
  public double systemTimeS;
  public double childUserTimeS;
  public double childSystemTimeS;

  public CpuMetrics() {}

  @Override
  public CpuMetrics set(CpuMetrics metrics) {
    userTimeS = metrics.userTimeS;
    systemTimeS = metrics.systemTimeS;
    childUserTimeS = metrics.childUserTimeS;
    childSystemTimeS = metrics.childSystemTimeS;
    return this;
  }

  @Override
  public CpuMetrics sum(@Nullable CpuMetrics b, @Nullable CpuMetrics output) {
    if (output == null) {
      output = new CpuMetrics();
    }

    if (b == null) {
      output.set(this);
    } else {
      output.systemTimeS = systemTimeS + b.systemTimeS;
      output.userTimeS = userTimeS + b.userTimeS;
      output.childSystemTimeS = childSystemTimeS + b.childSystemTimeS;
      output.childUserTimeS = childUserTimeS + b.childUserTimeS;
    }

    return output;
  }

  @Override
  public CpuMetrics diff(@Nullable CpuMetrics b, @Nullable CpuMetrics output) {
    if (output == null) {
      output = new CpuMetrics();
    }

    if (b == null) {
      output.set(this);
    } else {
      output.systemTimeS = systemTimeS - b.systemTimeS;
      output.userTimeS = userTimeS - b.userTimeS;
      output.childSystemTimeS = childSystemTimeS - b.childSystemTimeS;
      output.childUserTimeS = childUserTimeS - b.childUserTimeS;
    }

    return output;
  }

  @Override
  public boolean equals(@Nullable Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    CpuMetrics that = (CpuMetrics) other;

    return Double.compare(that.systemTimeS, systemTimeS) == 0
        && Double.compare(that.userTimeS, userTimeS) == 0
        && Double.compare(that.childSystemTimeS, childSystemTimeS) == 0
        && Double.compare(that.childUserTimeS, childUserTimeS) == 0;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(systemTimeS);
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(userTimeS);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(childSystemTimeS);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(childUserTimeS);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "CpuMetrics{"
        + "userTimeS="
        + userTimeS
        + ", systemTimeS="
        + systemTimeS
        + ", childUserTimeS="
        + childUserTimeS
        + ", childSystemTimeS="
        + childSystemTimeS
        + '}';
  }
}
