package com.petritkrasniqi.weatherapi.rest.controller;

import static java.util.Objects.nonNull;

import java.time.LocalDate;
import java.util.List;

import com.petritkrasniqi.weatherapi.exception.BadRequestException;

public class MetricsReportQuery {
	private List<Long> sensorIds;
	private boolean temperature;
	private boolean humidity;
	private boolean windSpeed;
	private LocalDate fromDate;
	private LocalDate toDate;
	private boolean includeWeatherData;
	
	public static class Builder {
		private List<Long> sensorIds;
		private boolean temperature;
		private boolean humidity;
		private boolean windSpeed;
		private LocalDate fromDate;
		private LocalDate toDate;
		private boolean includeWeatherData;
		
		public Builder sensorIds(List<Long> sensorIds) {
			this.sensorIds = sensorIds;
			return this;
		}
		
		public Builder temperature(boolean temperature) {
			this.temperature = temperature;
			return this;
		}
		
		public Builder humidity(boolean humidity) {
			this.humidity = humidity;
			return this;
		}
		
		public Builder windSpeed(boolean windSpeed) {
			this.windSpeed = windSpeed;
			return this;
		}
		
		public Builder fromDate(LocalDate fromDate) {
			this.fromDate = fromDate;
			return this;
		}
		
		public Builder toDate(LocalDate toDate) {
			this.toDate = toDate;
			return this;
		}
		
		public Builder includeWeatherData(boolean includeWeatherData) {
			this.includeWeatherData = includeWeatherData;
			return this;
		}
		
		public MetricsReportQuery build() {
			MetricsReportQuery query = new MetricsReportQuery();
			query.sensorIds = sensorIds;
			query.temperature = temperature;
			query.humidity = humidity;
			query.windSpeed = windSpeed;
			query.fromDate = fromDate;
			query.toDate = toDate;
			query.includeWeatherData = includeWeatherData;
			validate(query);
			
			return query;
		}

		private void validate(MetricsReportQuery query) {
			if(nonNull(query.fromDate) && nonNull(query.toDate)) {
				toDateCannotBeBeforeFromDate(query);
			}
			
		}

		private void toDateCannotBeBeforeFromDate(MetricsReportQuery query) {
			if (query.toDate.isBefore(query.fromDate))
				throw new BadRequestException("Invalid date range specified. To date cannot be earlier than the from date");
			
		}
		
	}

	public List<Long> getSensorIds() {
		return sensorIds;
	}

	public boolean isTemperaturePresent() {
		return temperature;
	}

	public boolean isHumidityPresent() {
		return humidity;
	}

	public boolean isWindSpeedPresent() {
		return windSpeed;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}
	
	public boolean isIncludeWeatherDataPresent() {
		return includeWeatherData;
	}

}
