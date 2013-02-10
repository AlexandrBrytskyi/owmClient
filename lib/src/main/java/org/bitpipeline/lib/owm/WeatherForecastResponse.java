/**
 * Copyright 2013 J. Miguel P. Tavares
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/
package org.bitpipeline.lib.owm;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author mtavares */
public class WeatherForecastResponse {
	static public class City {
		static private final String JSON_ID             = "id";
		static private final String JSON_COORD          = "coord";
		static private final String JSON_COUNTRY        = "country";
		static private final String JSON_NAME           = "name";
		static private final String JSON_DT_CALC        = "dt_calc";
		static private final String JSON_STATIONS_COUNT = "stations_count";

		private final int id;
		private final AbstractWeatherData.GeoCoord coord;
		private final String country;
		private final String name;
		private final long dtCalc;
		private final int stationsCount;

		public City (JSONObject json) throws JSONException {
			this.id = json.optInt (City.JSON_ID, Integer.MIN_VALUE);
			JSONObject jsonCoord = json.optJSONObject (City.JSON_COORD);
			if (jsonCoord != null)
				this.coord = new AbstractWeatherData.GeoCoord (jsonCoord);
			else
				this.coord = null;
			this.country = json.optString (City.JSON_COUNTRY);
			this.name = json.optString (City.JSON_NAME);
			this.dtCalc = json.optLong (City.JSON_DT_CALC, Long.MIN_VALUE);
			this.stationsCount = json.optInt (City.JSON_STATIONS_COUNT, Integer.MIN_VALUE);
		}

		public boolean hasId () {
			return this.id != Integer.MIN_VALUE;
		}
		public int getId () {
			return this.id;
		}

		public boolean hasCoordinates () {
			return this.coord != null;
		}
		public AbstractWeatherData.GeoCoord getCoordinates () {
			return this.coord;
		}

		public boolean hasCountryCode () {
			return this.country != null;
		}
		public String getCountryCode () {
			return this.country;
		}

		public boolean hasName () {
			return this.name != null;
		}
		public String getName () {
			return this.name;
		}

		public boolean hasDateTimeCalc () {
			return this.dtCalc != Long.MIN_VALUE;
		}
		public long getDateTimeCalc () {
			return this.dtCalc;
		}

		public boolean hasStationsCount () {
			return this.stationsCount != Integer.MIN_VALUE;
		}
		public int getStationsCount () {
			return this.stationsCount;
		}
	}

	static private final String JSON_COD = "cod";
	static private final String JSON_MESSAGE = "message";
	static private final String JSON_CALCTIME = "calctime";
	static private final String JSON_URL = "url";
	static private final String JSON_CITY = "city";
	static private final String JSON_UNITS = "units";
	static private final String JSON_MODEL = "model";
	static private final String JSON_LIST = "list";

	private final int code;
	private final String message;
	private final double calctime;
	private final String url;
	private final City city;
	private final String units;
	private final String model;
	private final List<ForecastWeatherData> forecasts;

	/**
	 * @throws JSONException 
	 *  */
	public WeatherForecastResponse (JSONObject json) throws JSONException {
		this.code = json.optInt (WeatherForecastResponse.JSON_COD, Integer.MIN_VALUE);
		this.message = json.optString (WeatherForecastResponse.JSON_MESSAGE);
		this.calctime = json.optDouble (WeatherForecastResponse.JSON_CALCTIME, Double.NaN);
		this.url = json.optString (WeatherForecastResponse.JSON_URL);
		JSONObject jsonCity = json.optJSONObject (WeatherForecastResponse.JSON_CITY);
		if (jsonCity != null)
			this.city = new City (jsonCity);
		else
			this.city = null;
		this.units = json.optString (WeatherForecastResponse.JSON_UNITS);
		this.model = json.optString (WeatherForecastResponse.JSON_MODEL);
		JSONArray jsonForecasts = json.optJSONArray (WeatherForecastResponse.JSON_LIST);
		if (jsonForecasts != null) {
			this.forecasts = new ArrayList<ForecastWeatherData> (jsonForecasts.length ());
			for (int i = 0; i<jsonForecasts.length (); i++) {
				this.forecasts.add (new ForecastWeatherData (jsonForecasts.getJSONObject (i)));
			}
		} else {
			this.forecasts = null;
		}
	}

	public boolean hasCode () {
		return this.code != Integer.MIN_VALUE;
	}
	public int getCode () {
		return this.code;
	}

	public boolean hasMessage () {
		return this.message != null && !this.message.isEmpty ();
	}
	public String getMessage () {
		return this.message;
	}

	public boolean hasCalcTime () {
		return this.calctime != Double.NaN;
	}
	public double getCalcTime () {
		return this.calctime;
	}

	public boolean hasUrl () {
		return this.url != null && !this.url.isEmpty ();
	}
	public String getUrl () {
		return this.url;
	}

	public boolean hasCity () {
		return this.city != null;
	}
	public City getCity () {
		return this.city;
	}

	public boolean hasUnits () {
		return this.units != null && !this.units.isEmpty ();
	}
	public String getUnits () {
		return this.units;
	}

	public boolean hasModel () {
		return this.model != null && !this.model.isEmpty ();
	}
	public String getModel () {
		return this.model;
	}

	public boolean hasForecasts () {
		return this.forecasts != null && !this.forecasts.isEmpty ();
	}
	public List<ForecastWeatherData> getForecasts () {
		return this.forecasts;
	}
}
