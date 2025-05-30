package com.bignerdranch.android.worldwise.database;

public class CountryDbSchema {
    public static final class CountryTable {
        public static final String NAME = "learned_countries";

        public static final class Cols {
            public static final String COUNTRY_NAME = "country_name";
        }
    }
}


    /*public static final class TopicTable {
        public static final String NAME = "topics";

        public static final class Cols {
            public static final String COUNTRY_UUID = "country_uuid";
            public static final String NAME = "name";
            //public static final String VIDEO_URL = "video_url";
            //public static final String FUN_FACTS = "fun_facts"; // maybe a single string with delimiter
            //public static final String IS_VIEWED = "is_viewed"; // 0 or 1
        }
    }

    public static final class QuestionTable {
        public static final String NAME = "questions";

        public static final class Cols {
            public static final String COUNTRY_UUID = "country_uuid";
            public static final String QUESTION = "question";
            public static final String ANSWERS = "answers";
            public static final String CORRECT_ANSWER_INDEX = "index";
        }
    }

    public static final class QuizTable {
        public static final String NAME = "quiz";

        public static final class Cols {
            public static final String COUNTRY_UUID = "country_uuid";
            public static final String QUESTIONS = "questions";
            public static final String SCORE = "score";
            public static final String DONE = "done";
            public static final String QUESTION_INDEX = "qindex";
        }
    }*/