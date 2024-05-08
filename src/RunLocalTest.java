import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Modifier;

/**
 * A framework to run public test cases for PJ4.
 *
 * <p>Purdue University -- CS18000 -- Spring 2021</p>
 *
 * @author J Morris Purdue CS
 * @version Mar 08, 2024
 */

@RunWith(Enclosed.class)
public class RunLocalTest {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {


        @Test(timeout = 1000)
        public void InvalidMazeExceptionTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = InvalidMazeException.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `InvalidMazeException` is `public`!",
                    Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `InvalidMazeException` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `InvalidMazeException` extends `Exception`!",
                    Exception.class, superclass);
            Assert.assertEquals("Ensure that `InvalidMazeException` implements no interfaces!",
                    0, superinterfaces.length);
        }

        @Test(timeout = 1000)
        public void MazeClassDeclarationTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = Maze.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `Maze` is `public`!",
                    Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `Maze` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `Maze` extends `Object`!",
                    Object.class, superclass);
            Assert.assertEquals("Ensure that `Maze` implements no interfaces!",
                    0, superinterfaces.length);
        }

        @Test(timeout = 1000)
        public void MazeSolverClassDeclarationTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = MazeSolver.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `MazeSolver` is `public`!",
                    Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `MazeSolver` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `MazeSolver` extends `Object`!",
                    Object.class, superclass);
            Assert.assertEquals("Ensure that `MazeSolver` implements no interfaces!",
                    0, superinterfaces.length);
        }

        @Test(timeout = 1000)
        public void runTestMazeSolver() {
            String input = "StarterMaze.txt";
            String output = "StarterMazeSolution.txt";


            MazeSolver testSolver = new MazeSolver();

            try {
                testSolver.readMaze(input);
                testSolver.solveMaze();
                testSolver.writeSolution(output);

            } catch (InvalidMazeException e) {
                Assert.assertTrue("MazeSolver's readMaze threw an unexpected InvalidMazeException\n" +
                        "Make sure readMaze is correctly creating the maze in StarterMaze.txt.", false);
            } catch (Exception f) {
                Assert.assertTrue("An IO exception was encountered while reading StarterMaze.txt\n" +
                        "Make sure readMaze is correctly reading the file.", false);
            }

            String expectedOutput = "StarterMaze\n" +
                    "Moves: 48\n" +
                    "Start\n" +
                    "6-0\n" +
                    "6-1\n" +
                    "6-2\n" +
                    "7-2\n" +
                    "8-2\n" +
                    "9-2\n" +
                    "10-2\n" +
                    "11-2\n" +
                    "12-2\n" +
                    "13-2\n" +
                    "14-2\n" +
                    "14-3\n" +
                    "14-4\n" +
                    "14-5\n" +
                    "14-6\n" +
                    "14-7\n" +
                    "14-8\n" +
                    "13-8\n" +
                    "12-8\n" +
                    "11-8\n" +
                    "10-8\n" +
                    "9-8\n" +
                    "8-8\n" +
                    "7-8\n" +
                    "6-8\n" +
                    "5-8\n" +
                    "5-7\n" +
                    "5-6\n" +
                    "6-6\n" +
                    "7-6\n" +
                    "8-6\n" +
                    "9-6\n" +
                    "9-5\n" +
                    "9-4\n" +
                    "8-4\n" +
                    "7-4\n" +
                    "6-4\n" +
                    "5-4\n" +
                    "4-4\n" +
                    "3-4\n" +
                    "2-4\n" +
                    "1-4\n" +
                    "1-5\n" +
                    "1-6\n" +
                    "1-7\n" +
                    "1-8\n" +
                    "1-9\n" +
                    "1-10\n" +
                    "End\n";

            String actualOutput = "";

            try (BufferedReader reader = new BufferedReader(new FileReader(output))) {
                String in = "";
                while ((in = reader.readLine()) != null) {
                    actualOutput += in + "\n";
                }
            } catch (IOException a) {
                Assert.assertTrue("An IO exception was encountered while reading StarterMazeSolution.txt\n" +
                        "Make sure writeSolution is writing to the given file.", false);
            } catch (Exception e) {
                Assert.assertTrue("An unknown exception was encountered", false);
            }

            Assert.assertEquals("Ensure that Maze's pathString method and MazeSolver's solveMaze are generating the correct solutions.",
                    expectedOutput.trim(), actualOutput.trim());

        }


    }

}