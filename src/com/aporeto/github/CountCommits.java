package com.aporeto.github;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONTokener;

import com.aporeto.helper.Constants;

public class CountCommits {

	public static void main(String args[]) throws IOException, URISyntaxException, ParseException {
		int pageNo = 1;

		boolean breakFlag = true;

		// getting last year's date from current date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		Date prevYear = cal.getTime();

		// HashMap for storing counts for each month
		HashMap<Integer, Integer> commitCounts = new HashMap<>();
		commitCounts.put(0, 0);
		commitCounts.put(1, 0);
		commitCounts.put(2, 0);
		commitCounts.put(3, 0);
		commitCounts.put(4, 0);
		commitCounts.put(5, 0);
		commitCounts.put(6, 0);
		commitCounts.put(7, 0);
		commitCounts.put(8, 0);
		commitCounts.put(9, 0);
		commitCounts.put(10, 0);
		commitCounts.put(11, 0);

		while (breakFlag) {
			String REPO_URL = Constants.GITHUB_COMMIT_REPO_URL + "&&page=" + pageNo;
			
			URI repoUri = new URI(REPO_URL);

			JSONTokener repoTokener = new JSONTokener(repoUri.toURL().openStream());
			JSONArray repoArr = new JSONArray(repoTokener);

			for (int j = 0; j < repoArr.length(); j++) {
				Date commitDate = dateFormat.parse(repoArr.getJSONObject(j).getJSONObject("commit")
						.getJSONObject("committer").getString("date").substring(0, 10));

				if (prevYear.before(commitDate)) {
					try {
						int count = commitCounts.get(commitDate.getMonth()) + 1;
						commitCounts.put(commitDate.getMonth(), count);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					breakFlag = false;
					break;
				}
			}
			pageNo++;
		}

		Iterator it = commitCounts.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			it.remove();
		}

	}

}
