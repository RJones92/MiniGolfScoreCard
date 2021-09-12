import { getAllTournaments } from "../../services/tournamentService";

const mockResponse = [
  {
    id: 1,
    year: "2016",
    courses: [
      {
        id: 1,
        courseName: "2016 course 1",
        holes: [
          {
            id: 1,
            par: 0,
            holeNumber: 1,
          },
        ],
        winnersByTournamentId: {
          1: {
            id: 3,
            firstName: "Jamie",
            lastName: "C",
            countOfTournamentsPlayed: 0,
            countOfTournamentsWon: 0,
            countOfCoursesPlayed: 0,
            countOfCoursesWon: 0,
            countOfHolesPlayed: 0,
            countOfHolesWon: 0,
          },
        },
      },
      {
        id: 2,
        courseName: "2016 course 2",
        holes: [
          {
            id: 19,
            par: 0,
            holeNumber: 1,
          },
        ],
        winnersByTournamentId: {
          1: {
            id: 3,
            firstName: "Jamie",
            lastName: "C",
            countOfTournamentsPlayed: 0,
            countOfTournamentsWon: 0,
            countOfCoursesPlayed: 0,
            countOfCoursesWon: 0,
            countOfHolesPlayed: 0,
            countOfHolesWon: 0,
          },
        },
      },
      {
        id: 3,
        courseName: "2016 course 3",
        holes: [
          {
            id: 37,
            par: 0,
            holeNumber: 1,
          },
        ],
        winnersByTournamentId: {
          1: {
            id: 3,
            firstName: "Jamie",
            lastName: "C",
            countOfTournamentsPlayed: 0,
            countOfTournamentsWon: 0,
            countOfCoursesPlayed: 0,
            countOfCoursesWon: 0,
            countOfHolesPlayed: 0,
            countOfHolesWon: 0,
          },
        },
      },
    ],
    players: [
      {
        id: 1,
        firstName: "Rhys",
        lastName: "A",
        countOfTournamentsPlayed: 0,
        countOfTournamentsWon: 0,
        countOfCoursesPlayed: 0,
        countOfCoursesWon: 0,
        countOfHolesPlayed: 0,
        countOfHolesWon: 0,
      },
      {
        id: 2,
        firstName: "Thomas",
        lastName: "B",
        countOfTournamentsPlayed: 0,
        countOfTournamentsWon: 0,
        countOfCoursesPlayed: 0,
        countOfCoursesWon: 0,
        countOfHolesPlayed: 0,
        countOfHolesWon: 0,
      },
      {
        id: 3,
        firstName: "Jamie",
        lastName: "C",
        countOfTournamentsPlayed: 0,
        countOfTournamentsWon: 0,
        countOfCoursesPlayed: 0,
        countOfCoursesWon: 0,
        countOfHolesPlayed: 0,
        countOfHolesWon: 0,
      },
    ],
    winner: {
      id: 3,
      firstName: "Jamie",
      lastName: "C",
      countOfTournamentsPlayed: 0,
      countOfTournamentsWon: 0,
      countOfCoursesPlayed: 0,
      countOfCoursesWon: 0,
      countOfHolesPlayed: 0,
      countOfHolesWon: 0,
    },
  },
  {
    id: 2,
    year: "2017",
    courses: [
      {
        id: 6,
        courseName: "2017 course 1",
        holes: [
          {
            id: 91,
            par: 0,
            holeNumber: 1,
          },
          {
            id: 92,
            par: 0,
            holeNumber: 2,
          },
        ],
        winnersByTournamentId: {
          2: {
            id: 3,
            firstName: "Jamie",
            lastName: "C",
            countOfTournamentsPlayed: 0,
            countOfTournamentsWon: 0,
            countOfCoursesPlayed: 0,
            countOfCoursesWon: 0,
            countOfHolesPlayed: 0,
            countOfHolesWon: 0,
          },
        },
      },
      {
        id: 7,
        courseName: "2017 course 2",
        holes: [
          {
            id: 109,
            par: 2,
            holeNumber: 0,
          },
          {
            id: 110,
            par: 2,
            holeNumber: 1,
          },
        ],
        winnersByTournamentId: {
          2: {
            id: 3,
            firstName: "Jamie",
            lastName: "C",
            countOfTournamentsPlayed: 0,
            countOfTournamentsWon: 0,
            countOfCoursesPlayed: 0,
            countOfCoursesWon: 0,
            countOfHolesPlayed: 0,
            countOfHolesWon: 0,
          },
        },
      },
    ],
    players: [
      {
        id: 1,
        firstName: "Rhys",
        lastName: "A",
        countOfTournamentsPlayed: 0,
        countOfTournamentsWon: 0,
        countOfCoursesPlayed: 0,
        countOfCoursesWon: 0,
        countOfHolesPlayed: 0,
        countOfHolesWon: 0,
      },
      {
        id: 2,
        firstName: "Thomas",
        lastName: "B",
        countOfTournamentsPlayed: 0,
        countOfTournamentsWon: 0,
        countOfCoursesPlayed: 0,
        countOfCoursesWon: 0,
        countOfHolesPlayed: 0,
        countOfHolesWon: 0,
      },
      {
        id: 3,
        firstName: "Jamie",
        lastName: "C",
        countOfTournamentsPlayed: 0,
        countOfTournamentsWon: 0,
        countOfCoursesPlayed: 0,
        countOfCoursesWon: 0,
        countOfHolesPlayed: 0,
        countOfHolesWon: 0,
      },
    ],
    winner: {
      id: 3,
      firstName: "Jamie",
      lastName: "C",
      countOfTournamentsPlayed: 0,
      countOfTournamentsWon: 0,
      countOfCoursesPlayed: 0,
      countOfCoursesWon: 0,
      countOfHolesPlayed: 0,
      countOfHolesWon: 0,
    },
  },
  {
    id: 3,
    year: "2018",
    courses: [
      {
        id: 13,
        courseName: "2018 course 1",
        holes: [
          {
            id: 208,
            par: 0,
            holeNumber: 1,
          },
          {
            id: 209,
            par: 0,
            holeNumber: 2,
          },
          {
            id: 210,
            par: 0,
            holeNumber: 3,
          },
        ],
        winnersByTournamentId: {
          3: {
            id: 3,
            firstName: "Jamie",
            lastName: "C",
            countOfTournamentsPlayed: 0,
            countOfTournamentsWon: 0,
            countOfCoursesPlayed: 0,
            countOfCoursesWon: 0,
            countOfHolesPlayed: 0,
            countOfHolesWon: 0,
          },
        },
      },
      {
        id: 14,
        courseName: "2018 course 2",
        holes: [
          {
            id: 220,
            par: 2,
            holeNumber: 0,
          },
          {
            id: 221,
            par: 3,
            holeNumber: 1,
          },
          {
            id: 222,
            par: 4,
            holeNumber: 2,
          },
        ],
        winnersByTournamentId: {
          3: {
            id: 3,
            firstName: "Jamie",
            lastName: "C",
            countOfTournamentsPlayed: 0,
            countOfTournamentsWon: 0,
            countOfCoursesPlayed: 0,
            countOfCoursesWon: 0,
            countOfHolesPlayed: 0,
            countOfHolesWon: 0,
          },
        },
      },
    ],
    players: [
      {
        id: 1,
        firstName: "Rhys",
        lastName: "A",
        countOfTournamentsPlayed: 0,
        countOfTournamentsWon: 0,
        countOfCoursesPlayed: 0,
        countOfCoursesWon: 0,
        countOfHolesPlayed: 0,
        countOfHolesWon: 0,
      },
      {
        id: 2,
        firstName: "Thomas",
        lastName: "B",
        countOfTournamentsPlayed: 0,
        countOfTournamentsWon: 0,
        countOfCoursesPlayed: 0,
        countOfCoursesWon: 0,
        countOfHolesPlayed: 0,
        countOfHolesWon: 0,
      },
      {
        id: 3,
        firstName: "Jamie",
        lastName: "C",
        countOfTournamentsPlayed: 0,
        countOfTournamentsWon: 0,
        countOfCoursesPlayed: 0,
        countOfCoursesWon: 0,
        countOfHolesPlayed: 0,
        countOfHolesWon: 0,
      },
    ],
    winner: {
      id: 3,
      firstName: "Jamie",
      lastName: "C",
      countOfTournamentsPlayed: 0,
      countOfTournamentsWon: 0,
      countOfCoursesPlayed: 0,
      countOfCoursesWon: 0,
      countOfHolesPlayed: 0,
      countOfHolesWon: 0,
    },
  },
];

beforeEach(() => {
  fetch.resetMocks();
});

test("returns a list of tournaments", async () => {
  fetch.mockResponseOnce(JSON.stringify(mockResponse));
  const allTourns = await getAllTournaments();
  expect(allTourns.length).toBe(3);
  expect(fetch.mock.calls.length).toBe(1);
});
