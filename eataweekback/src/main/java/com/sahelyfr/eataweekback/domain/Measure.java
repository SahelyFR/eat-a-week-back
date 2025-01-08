package com.sahelyfr.eataweekback.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Measure {

  private String name;
  private String shortName;
  private boolean enabled;
  private LocalDateTime creationDate;
  private LocalDateTime lastUpdateDate;

}
