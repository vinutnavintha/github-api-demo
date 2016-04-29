package com.aporeto.helper;

public class Constants {

	public static final String ACCESS_TOKEN = "e9792c66cdb320ad7a2771725c32c9d63d687e8a";
	public static final String GITHUB_API_URL = "https://api.github.com/";

	// user for which commits and pull requests are counted
	public static final String GITHUB_USER = "chartjs";
	
	// repo for which commits and pull requests are counted
	public static final String GITHUB_REPO = "Chart.js";

	public static final String GITHUB_COMMIT_REPO_URL = "https://api.github.com/repos/" + GITHUB_USER + "/"
			+ GITHUB_REPO + "/commits?access_token=" + ACCESS_TOKEN;

	public static final String GITHUB_PULL_REPO_URL = "https://api.github.com/repos/" + GITHUB_USER + "/" + GITHUB_REPO
			+ "/pulls?access_token=" + ACCESS_TOKEN;

}
