import { getAllScores } from "../../services/scoreService";

const mockResponse = [
  {
    id: 1,
    player: {
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
    tournament: {
      id: 1,
      year: "2016",
      courses: [
        {
          id: 1,
          courseName: "2016 course 1",
          holes: [],
          winnersByTournamentId: {},
        },
        {
          id: 2,
          courseName: "2016 course 2",
          holes: [],
          winnersByTournamentId: {},
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
      winner: null,
    },
    hole: {
      id: 1,
      par: 0,
      holeNumber: 1,
    },
    strokes: 2,
  },
  {
    id: 2,
    player: {
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
    tournament: {
      id: 1,
      year: "2016",
      courses: [
        {
          id: 1,
          courseName: "2016 course 1",
          holes: [],
          winnersByTournamentId: {},
        },
        {
          id: 2,
          courseName: "2016 course 2",
          holes: [],
          winnersByTournamentId: {},
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
      winner: null,
    },
    hole: {
      id: 2,
      par: 0,
      holeNumber: 2,
    },
    strokes: 4,
  },
];

beforeEach(() => {
  fetch.resetMocks();
});

test("returns a list of scores", async () => {
  fetch.mockResponseOnce(JSON.stringify(mockResponse));
  const allScores = await getAllScores();
  expect(allScores.length).toBe(2);
  expect(fetch.mock.calls.length).toBe(1);
});
