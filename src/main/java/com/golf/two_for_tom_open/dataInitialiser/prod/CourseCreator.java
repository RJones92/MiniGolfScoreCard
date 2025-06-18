package com.golf.two_for_tom_open.dataInitialiser.prod;

import com.golf.two_for_tom_open.model.entity.Course;
import com.golf.two_for_tom_open.model.entity.Hole;

import java.time.Year;
import java.util.*;
import java.util.stream.Stream;

public class CourseCreator {

    public List<Course> create(Year year) {
        return switch (year.getValue()) {
            case 2016 -> buildCourses_2016();
            case 2017 -> buildCourses_2017();
            case 2018 -> buildCourses_2018();
            case 2019 -> buildCourses_2019();
            case 2020 -> buildCourses_2020();
            case 2021 -> buildCourses_2021();
            case 2022 -> buildCourses_2022();
            case 2023 -> buildCourses_2023();
            case 2024 -> buildCourses_2024();
            default -> throw new RuntimeException("Uh oh, there's no courses for that year!");
        };
    }
    
    private List<Course> buildCourses_2016() {
        Map<String, Integer> courseNameWithNumberOfHoles = new LinkedHashMap<>();
        courseNameWithNumberOfHoles.put("Hastings course 1", 18);
        courseNameWithNumberOfHoles.put("Hastings course 2", 18);
        courseNameWithNumberOfHoles.put("Hastings course 3", 18);
        courseNameWithNumberOfHoles.put("Brighton Jungle Rumble course 1", 18);
        courseNameWithNumberOfHoles.put("Brighton Caveman course", 18);
        return courseNameWithNumberOfHoles.entrySet()
                .stream()
                .map(entry -> createCourse(entry.getKey(), createHolesForCourseWithNoPar(entry.getValue())))
                .toList();
    }
    
    private List<Course> buildCourses_2017() {
        var courses_2017 = new LinkedList<Course>();
        courses_2017.add(createCourse("Congo Rapids Adventure Golf",
                createHolesForCourseWithNoPar(18)));
        courses_2017.add(createCourse("Clippesby Family Golf",
                createHoles(List.of(2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2,2,2,2,2,2,3,4))));
        courses_2017.add(createCourse("Lost world Adventure golf, Hemsby",
                createHoles(List.of(2,2,3,4,2,2,2,3,4))));
        courses_2017.add(createCourse("Stonehenge, the BIG minigolf",
                createHoles(List.of(3,2,3,3,4, 3,3,4,3, 3,4,3,3, 4,2,3,2,4))));
        courses_2017.add(createCourse("Castaway Island, Great Yarmouth",
                createHoles(List.of(2,2,3,2,2,3,3,2,3,2,3,3,2,3,2,3,2,3))));
        courses_2017.add(createCourse("Pirates Cover, Great Yarmouth",
                createHoles(List.of(2,3,3,3,4,3,2,2,3,3, 5,3,2,3,2,3,3,3))));
        courses_2017.add(createCourse("Congo Rapids Lost World Adventure Golf, Woodbridge",
                createHolesForCourseWithNoPar(18)));
        return courses_2017;
    }
    
    private List<Course> buildCourses_2018() {
        var courses_2018 = new LinkedList<Course>();
        courses_2018.add(createCourse("Bear Creek Adventure Golf",
                createHolesForCourseWithNoPar(12)));
        courses_2018.add(createCourse("The Pavilion Fun Park, Clacton",
                createHoles(List.of(2,3,4,3,4,4,4,3,3,3,3,4,4))));
        courses_2018.add(createCourse("Greensward Adventure Golf, Clacton-on-sea",
                createHolesForCourseWithNoPar(9)));
        courses_2018.add(createCourse("St Osyth Mini 12 Hole Adventure Golf",
                createHolesForCourseWithNoPar(12)));
        courses_2018.add(createCourse("Mighty Claws Adventure Golf, Colchester",
                createHoles(List.of(2,2,2,3,2,2,3,3,4,2,2,3,3,2,3,3,3,3))));
        courses_2018.add(createCourse("Mersea Island, Crazy golf",
                createHolesForCourseWithNoPar(9)));
        courses_2018.add(createCourse("Arnold Palmer Minigolf",
                createHoles(List.of(2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2))));
        courses_2018.add(createCourse("Pirates Bay Adventure Gold, Maldon",
                createHoles(List.of(2,3,3,2,3,2,2,3,2,3,2,3,3,3,5,2,3,4))));
        return courses_2018;
    }

    private List<Course> buildCourses_2019() {
        var courses_2019 = new LinkedList<Course>();
        courses_2019.add(createCourse("Jungle Falls Adventure Golf, Trent Park Country Club",
                createHoles(List.of(2,2,2,3,2,2,2,2,2,3,2,2,2,2,2,2,2,3))));
        courses_2019.add(createCourse("Captain's Bay Adventure Golf, London",
                createHoles(List.of(2,2,2,2,3,2,3,2,3))));
        courses_2019.add(createCourse("Lost Jungle London, Amazon course",
                createHoles(List.of(2,2,2,3,2,2,2,3,2,2,2,2,2,3,2,2,2,3))));
        courses_2019.add(createCourse("Lost Jungle London, Congo course",
                createHolesForCourseWithNoPar(18)));
        courses_2019.add(createCourse("TopGolf Adventure Golf",
                createHoles(List.of(3,2,4,3,3,3,3,3,4,2,3,4,3,4,3,3,3,4))));
        courses_2019.add(createCourse("Dinosaur Safari Adventure Golf",
                createHoles(List.of(2,2,2,3,2,2,2,3,2,2,2,2,2,2,3,2,3,2))));
        courses_2019.add(createCourse("Mr Mulligan's Jaws-some Journeys - Lost World Jungle Explorer, Stevenage",
                createHoles(List.of(2,2,2,2,3,2,2,2,3,2,3,2,2,2,3,2,2,2))));
        courses_2019.add(createCourse("Mr Mulligan's Jaws-some Journeys - OCean Adventures, Stevenage",
                createHoles(List.of(2,2,3,2,2,3,2,2,2,3,2,2,2,2,2,3,2,2))));
        courses_2019.add(createCourse("Glo Crazy",
                createHolesForCourseWithNoPar(12)));
        return courses_2019;
    }

    private List<Course> buildCourses_2020() {
        var courses_2020 = new LinkedList<Course>();
        courses_2020.add(createCourse("1066 Adventure Golf",
                createHoles(List.of(2,3,2,3,2,3,3,2,3,3,2,2,3,3,4,3,3,2))));
        courses_2020.add(createCourse("DIY minigolf, White Roding",
                createHolesForCourseWithNoPar(18)));
        List<Hole> farmyardHoles = createHolesForCourseWithNoPar(18);
        farmyardHoles.add(createHole(19,2));
        courses_2020.add(createCourse("Farmyard Crazy Golf, Broxbourne",
                farmyardHoles));
        courses_2020.add(createCourse("Jurassic Falls Adventure Golf",
                createHolesForCourseWithNoPar(18)));
        courses_2020.add(createCourse("Moby Adventure Golf",
                createHoles(List.of(2,2,3,2,2,3,2,3,2,2,2,2,3,2,2,2,3,2))));
        return courses_2020;
    }

    private List<Course> buildCourses_2021() {
        var courses_2021 = new LinkedList<Course>();
        courses_2021.add(createCourse("Herne Bay Minigolf",
                createHolesForCourseWithNoPar(18)));
        courses_2021.add(createCourse("Quex Adventure Golf",
                createHolesForCourseWithNoPar(18)));
        courses_2021.add(createCourse("Strokes Adventure Golf",
                createHolesForCourseWithNoPar(18)));
        courses_2021.add(createCourse("Lost Island Adventure Golf",
                createHolesForCourseWithNoPar(18)));
        courses_2021.add(createCourse("Lillyputt Minigolf",
                createHoles(List.of(2,2,2,2,2,2,2,2,2,2,2,2))));
        courses_2021.add(createCourse("Rascal Bay Manston",
                createHolesForCourseWithNoPar(18)));
        return courses_2021;
    }

    private List<Course> buildCourses_2022() {
        var courses_2022 = new LinkedList<Course>();
        courses_2022.add(createCourse("Pirate Cove Adventure Golf, Smugglers Course, DA9 95F",
                createHolesForCourseWithNoPar(18)));
        courses_2022.add(createCourse("Pirate Cove Adventure Golf, Pirates Course, DA9 95F",
                createHolesForCourseWithNoPar(18)));
        courses_2022.add(createCourse("Enchanted Village Adventure Golf, BR4 9BB",
                createHolesForCourseWithNoPar(12)));
        courses_2022.add(createCourse("Jurassic Encounter Adventure Golf, KT3 4PM",
                createHoles(List.of(2,2,3,2,2,2,3,2,2,2,2,2,3,2,3,2,3,2))));
        courses_2022.add(createCourse("Jungle Island, KT19 8QG",
                createHolesForCourseWithNoPar(18)));
        courses_2022.add(createCourse("Galloping MiniGolf, KT10 8AN",
                createHolesForCourseWithNoPar(12)));
        courses_2022.add(createCourse("Safari Adventure Golf, Hershom Golf Club, KT12 4RA",
                createHolesForCourseWithNoPar(18)));
        return courses_2022;
    }

    private List<Course> buildCourses_2023() {
        var courses_2023 = new LinkedList<Course>();
        courses_2023.add(createCourse("Meadow Croft Garden Centre, SS11 7QU",
                createHoles(List.of(2,2,2,3,2,2,3,2,3,2,2,2,3,2,2,3,2,3))));
        courses_2023.add(createCourse("Garon Castle Adventures, SS2 4FA",
                createHolesForCourseWithNoPar(18)));
        courses_2023.add(createCourse("Mr Mulligans Basildon, Game 1, SS14 3WB",
                createHolesForCourseWithNoPar(12)));
        courses_2023.add(createCourse("Mr Mulligans Basildon, Game 2, SS14 3WB",
                createHolesForCourseWithNoPar(12)));
        courses_2023.add(createCourse("Noahs Park Adventrue Golf, CM11 2UD",
                createHolesForCourseWithNoPar(18)));
        courses_2023.add(createCourse("Hackers, Explore Azura & Rosa, CM12 9BQ",
                createHolesForCourseWithNoPar(16)));
        courses_2023.add(createCourse("Rascal Bay Adventure Golf, CM1 2QT",
                createHolesForCourseWithNoPar(19)));
        courses_2023.add(createCourse("The Notelys Golf Club, CM8 1ST",
                createHolesForCourseWithNoPar(18)));
        return courses_2023;
    }

    private List<Course> buildCourses_2024() {
        return Stream.of(
                createCourse("Hastings Pirate course, TN34 3AJ", createHolesForCourseWithNoPar(18)),
                createCourse("Hastings Adventure course, TN34 3AJ", createHolesForCourseWithNoPar(18)),
                createCourse("Hastings Crazy Golf Course, TN34 3AJ", createHolesForCourseWithNoPar(18)),
                createCourse("Princes Mini Golf, BN22 7LQ",
                        createHoles(List.of(3,5,4,3,4,4,4,4,4,3,5,5,4,3,3,5,4,4))),
                createCourse("Pirate Adventure Golf, BN22 7AD",
                        createHoles(List.of(2,2,2,3,2,3,2,2,3,3,3,2,3,2,3,3,3,2))),
                createCourse("Orb Mini Golf, BN21 3EL", createHolesForCourseWithNoPar(9)),
                createCourse("Kings Crazy Golf, RH19 3DJ",
                        createHoles(List.of(3,2,3,3,3,3,3,3,3,3,3,2,2,3,3,3,3,3)))
        ).toList();
    }
    
    private Course createCourse(String courseName, List<Hole> holesForCourse) {
        return Course.builder()
                .courseName(courseName)
                .holes(holesForCourse)
                .build();
    }

    private List<Hole> createHolesForCourseWithNoPar(Integer numberOfHoles) {
        List<Hole> holes = new ArrayList<>();
        for (int i = 0; i < numberOfHoles; i++) {
            holes.add(createHole(i + 1, 0));
        }
        return holes;
    }

    private List<Hole> createHoles(List<Integer> orderedListOfPars) {
        List<Hole> holes = new LinkedList<>();
        for (int i = 0; i < orderedListOfPars.size(); i++) {
            holes.add(createHole(i, orderedListOfPars.get(i)));
        }
        return holes;
    }

    private Hole createHole(Integer holeNumber, Integer par) {
        return Hole.builder().holeNumber(holeNumber).par(par).build();
    }
}
