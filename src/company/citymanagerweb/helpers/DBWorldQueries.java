package company.citymanagerweb.helpers;

public class DBWorldQueries {
	public static String getCitiesByDistrictByPopulation() {
		return "select * from city" + "where CountryCode = 'SLO'"
				+ "order by District ASC, Population DESC";
	}

	public static String getCountriesByName() {
		return "select Code, Name, Population " + "from Country "
				+ "order by Name";
	}
}
