/*
 * Copyright (C) 2014 Mukesh Y authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.tairoroberto.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Mukesh Y
 */
public class Utility {
	public static ArrayList<String> nameOfEvent = new ArrayList<String>();
	public static ArrayList<String> startDates = new ArrayList<String>();
	public static ArrayList<String> endDates = new ArrayList<String>();
	public static ArrayList<String> descriptions = new ArrayList<String>();
    public static ArrayList<String> startHour = new ArrayList<String>();
    public static ArrayList<String> endHour = new ArrayList<String>();

	public static ArrayList<String> readCalendarEvent(Context context) {
		Cursor cursor = context.getContentResolver()
				.query(Uri.parse("content://com.android.calendar/events"),
						new String[] { "calendar_id", "title", "description",
								"dtstart", "dtend", "eventLocation"}, null,
						null, null);
		cursor.moveToFirst();
		// fetching calendars name
		String CNames[] = new String[cursor.getCount()];

		// fetching calendars id
		nameOfEvent.clear();
		startDates.clear();
		endDates.clear();
		descriptions.clear();
        startHour.clear();
        endHour.clear();
		for (int i = 0; i < CNames.length; i++) {


            if (cursor.getString(1) != null){
                nameOfEvent.add(cursor.getString(1));
            }
            if (cursor.getString(3) != null){
                startDates.add(getDate(Long.parseLong(cursor.getString(3))));
            }
			if (cursor.getString(4) != null){
                endDates.add(getDate(Long.parseLong(cursor.getString(4))));
            }
			if (cursor.getString(2) != null){
                descriptions.add(cursor.getString(2));
            }
            if (cursor.getString(3) != null){
                startHour.add(getHour(Long.parseLong(cursor.getString(3))));
            }
            if (cursor.getString(4) != null){
                endHour.add(getHour(Long.parseLong(cursor.getString(4))));
            }

			CNames[i] = cursor.getString(1);
			cursor.moveToNext();

		}
		return nameOfEvent;
	}

	public static String getDate(long milliSeconds) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd")/*("yyyy-MM-dd")*/;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}

    public static String getHour(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")/*("yyyy-MM-dd")*/;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
