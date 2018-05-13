package com.example.user.lab_8_listview.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CourseContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Course> ITEMS = new ArrayList<Course>();
    public static final String[] subjects = new String[] { "Maths","Java","Industrial design","Web technologies","Physics","Sport"};
    public static final Double[] grades = new Double[] {6.00,5.92,4.22,4.44,2.34,3.55};


    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Course> ITEM_MAP = new HashMap<String, Course>();

    private static final int COUNT = 5;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createCourse(i));
        }
    }

    private static void addItem(Course item) {
            ITEMS.add(item);
            ITEM_MAP.put(item.getSubject(), item);
    }

    private static Course createCourse(int position) {
        return new Course(subjects[position],grades[position]);
    }


}
