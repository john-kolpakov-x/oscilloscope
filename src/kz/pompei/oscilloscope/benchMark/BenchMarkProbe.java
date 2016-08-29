package kz.pompei.oscilloscope.benchMark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;

import java.math.BigDecimal;

public class BenchMarkProbe {

  @Param({"1", "32", "3000", "700000"})
  long longParam;

  @Benchmark
  public BigDecimal testingMethod() {
    return BigDecimal.TEN.multiply(BigDecimal.valueOf(longParam));
  }

}
