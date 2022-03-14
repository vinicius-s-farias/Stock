package com.rest.api.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
    public class StockDto {
        private Long id;
        private Double askMin;
        private Double askMax;
        private Double bidMin;
        private Double bidMax;

        public StockDto(Long id, Double askMin, Double askMax, Double bidMin, Double bidMax) {
            this.id = id;
            this.askMin = askMin;
            this.askMax = askMax;
            this.bidMin = bidMin;
            this.bidMax = bidMax;
        }


}
