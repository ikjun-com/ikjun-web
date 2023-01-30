package com.ikjunweb.entity.type;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.ikjunweb.entity.board.QBoard.board;

public enum SubjectType {
    PROGRAMMING_1, PROGRAMMING_2, COMPUTER_MATH_1, COMPUTER_MATH_2, COMPUTER_CALCULUS, PROBABILITY_STATISTICS, COMPUTER_ENGINEERING,
    PHYSICS, DISCRETE_MATH, LINUX_SYSTEM, ENGINEERING_DESIGN,
    ART_TECHNOLOGY, MEDIA;

    public static Map<SubjectType, String> getSubjectTypeMap() {
        Map<SubjectType, String> result = new ConcurrentHashMap<>();
        for (SubjectType subjectType : SubjectType.values()) {
            if (subjectType == SubjectType.PROGRAMMING_1) {
                result.put(SubjectType.PROGRAMMING_1, "프로그래밍 및 실습 1");
            } else if (subjectType == SubjectType.PROGRAMMING_2) {
                result.put(SubjectType.PROGRAMMING_2, "프로그래밍 및 실습 2");
            } else if (subjectType == SubjectType.COMPUTER_MATH_1) {
                result.put(SubjectType.COMPUTER_MATH_1, "컴퓨터수학 1");
            } else if (subjectType == SubjectType.COMPUTER_MATH_2) {
                result.put(SubjectType.COMPUTER_MATH_2, "컴퓨터수학 2");
            } else if (subjectType == SubjectType.COMPUTER_CALCULUS) {
                result.put(SubjectType.COMPUTER_CALCULUS, "컴퓨터 미적분 활용");
            } else if (subjectType == SubjectType.PROBABILITY_STATISTICS) {
                result.put(SubjectType.PROBABILITY_STATISTICS, "확률과 통계");
            } else if (subjectType == SubjectType.COMPUTER_ENGINEERING) {
                result.put(SubjectType.COMPUTER_ENGINEERING, "컴퓨터 공학 기초");
            } else if (subjectType == SubjectType.PHYSICS) {
                result.put(SubjectType.PHYSICS, "물리 1 및 실험");
            } else if (subjectType == SubjectType.DISCRETE_MATH) {
                result.put(SubjectType.DISCRETE_MATH, "이산수학");
            } else if (subjectType == SubjectType.LINUX_SYSTEM) {
                result.put(SubjectType.LINUX_SYSTEM, "리눅스 시스템 관리");
            } else if (subjectType == SubjectType.ENGINEERING_DESIGN) {
                result.put(SubjectType.ENGINEERING_DESIGN, "창의적 공학 설계");
            } else if (subjectType == SubjectType.ART_TECHNOLOGY) {
                result.put(SubjectType.ART_TECHNOLOGY, "art & technology");
            } else if (subjectType == SubjectType.MEDIA) {
                result.put(SubjectType.MEDIA, "미디어 제작 및 실습");
            }
        }
        return result;
    }

    public static String getSubjectType(SubjectType subjectType) {
        if (subjectType == SubjectType.PROGRAMMING_1) return "프로그래밍 및 실습 1";
        else if (subjectType == SubjectType.PROGRAMMING_2) return "프로그래밍 및 실습 2";
        else if (subjectType == SubjectType.COMPUTER_MATH_1) return "컴퓨터수학 1";
        else if (subjectType == SubjectType.COMPUTER_MATH_2) return "컴퓨터수학 2";
        else if (subjectType == SubjectType.COMPUTER_CALCULUS) return "컴퓨터 미적분 활용";
        else if (subjectType == SubjectType.PROBABILITY_STATISTICS) return "확률과 통계";
        else if (subjectType == SubjectType.COMPUTER_ENGINEERING) return "컴퓨터 공학 기초";
        else if (subjectType == SubjectType.PHYSICS) return "물리 1 및 실험";
        else if (subjectType == SubjectType.DISCRETE_MATH) return "이산수학";
        else if (subjectType == SubjectType.LINUX_SYSTEM) return "리눅스 시스템 관리";
        else if (subjectType == SubjectType.ENGINEERING_DESIGN) return "창의적 공학 설계";
        else if (subjectType == SubjectType.ART_TECHNOLOGY) return "art & technology";
        else if (subjectType == SubjectType.MEDIA) return "미디어 제작 및 실습";
        return "찾을 수 없음";
    }
}
