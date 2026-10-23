package fr.univartois.butinfo.fractalens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import fr.univartois.butinfo.fractalens.color.ColorPaletteType;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType;
import picocli.CommandLine;

/**
 * The FractalCliTest class tests the command-line interface implemented by {@link FractalCli}.
 *
 * @author Maxence Beauvais
 * @version 1.0
 */
class FractalCliTest {

    /**
     * The exit code returned by Picocli when the command-line is valid and executed.
     */
    private static final int OK = 0;

    /**
     * The exit code returned by Picocli when an exception is thrown during execution.
     */
    private static final int EXECUTION_ERROR = 1;

    /**
     * The exit code returned by Picocli when the command-line is invalid.
     */
    private static final int USAGE_ERROR = 2;

    /**
     * A temporary directory in which the generated files are written.
     */
    @TempDir
    Path tempDir;

    /**
     * The standard output of the command.
     */
    private final StringWriter out = new StringWriter();

    /**
     * The error output of the command.
     */
    private final StringWriter err = new StringWriter();

    /**
     * Executes the command with the given arguments.
     *
     * @param args The arguments of the command.
     *
     * @return The exit code of the command.
     */
    private int execute(String... args) {
        CommandLine commandLine = new CommandLine(new FractalCli());
        commandLine.setOut(new PrintWriter(out));
        commandLine.setErr(new PrintWriter(err));
        return commandLine.execute(args);
    }

    /**
     * Checks that the given file exists and is not empty.
     *
     * @param file The file to check.
     *
     * @throws Exception If the size of the file cannot be read.
     */
    private static void assertGenerated(Path file) throws Exception {
        assertTrue(Files.exists(file), "The file " + file + " should have been generated.");
        assertTrue(Files.size(file) > 0, "The file " + file + " should not be empty.");
    }

    /**
     * Test method for a single image generated with the default options
     * (except for the size and the iterations, reduced to speed up the test).
     */
    @Test
    void testSingleImageWithDefaultOptions() throws Exception {
        Path file = tempDir.resolve("default.png");
        int exitCode = execute("-w", "20", "-h", "20", "-n", "10", "-o", file.toString());
        assertEquals(OK, exitCode);
        assertGenerated(file);
    }

    /**
     * Test method for a single image with every option of the command.
     */
    @Test
    void testSingleImageWithAllOptions() throws Exception {
        Path file = tempDir.resolve("all.png");
        int exitCode = execute(
                "--fractal-name", "JULIA",
                "--height", "15",
                "--width", "25",
                "--scale", "0.01",
                "--focus-x", "-0.75",
                "--focus-y", "0.1",
                "--nb-iterations", "20",
                "--palette-name", "GRADIENT_BLUE_BLACK",
                "--type", "single",
                "--output", file.toString());
        assertEquals(OK, exitCode);
        assertGenerated(file);
    }

    /**
     * Test method for every fractal that can be chosen with the option {@code -f}.
     *
     * @param sequence The type of sequence to use.
     */
    @ParameterizedTest
    @EnumSource(ComplexSequenceType.class)
    void testEveryFractal(ComplexSequenceType sequence) throws Exception {
        Path file = tempDir.resolve(sequence.name() + ".png");
        int exitCode = execute("-f", sequence.name(), "-w", "10", "-h", "10", "-n", "10", "-o", file.toString());
        assertEquals(OK, exitCode);
        assertGenerated(file);
    }

    /**
     * Test method for every palette that can be chosen with the option {@code -p}.
     *
     * @param palette The type of palette to use.
     */
    @ParameterizedTest
    @EnumSource(ColorPaletteType.class)
    void testEveryPalette(ColorPaletteType palette) throws Exception {
        Path file = tempDir.resolve(palette.name() + ".png");
        int exitCode = execute("-p", palette.name(), "-w", "10", "-h", "10", "-n", "10", "-o", file.toString());
        assertEquals(OK, exitCode);
        assertGenerated(file);
    }

    /**
     * Test method for a zoom animation.
     */
    @Test
    void testZoomAnimation() throws Exception {
        Path file = tempDir.resolve("zoom.gif");
        int exitCode = execute("-t", "zoom", "--frames", "3", "--zoom-factor", "0.5", "--delay", "10",
                "-w", "10", "-h", "10", "-n", "10", "-o", file.toString());
        assertEquals(OK, exitCode);
        assertGenerated(file);
    }

    /**
     * Test method for a rotation animation (the type is not case-sensitive).
     */
    @Test
    void testRotationAnimation() throws Exception {
        Path file = tempDir.resolve("rotation.gif");
        int exitCode = execute("-t", "ROTATION", "--frames", "3", "--delay", "10",
                "-w", "10", "-h", "10", "-n", "10", "-f", "JULIA", "-o", file.toString());
        assertEquals(OK, exitCode);
        assertGenerated(file);
    }

    /**
     * Test method for an unknown type of image, which falls back to a single image.
     */
    @Test
    void testUnknownTypeGeneratesSingleImage() throws Exception {
        Path file = tempDir.resolve("unknown.png");
        int exitCode = execute("-t", "unknown", "-w", "10", "-h", "10", "-n", "10", "-o", file.toString());
        assertEquals(OK, exitCode);
        assertGenerated(file);
    }

    /**
     * Test method for the option {@code --help}.
     */
    @Test
    void testHelp() {
        int exitCode = execute("--help");
        assertEquals(OK, exitCode);
        assertTrue(out.toString().contains("fractalens"), "The help should mention the name of the command.");
        assertTrue(out.toString().contains("--height"), "The help should list the options.");
    }

    /**
     * Test method for the options {@code -V} and {@code --version}.
     */
    @Test
    void testVersion() {
        assertEquals(OK, execute("-V"));
        assertEquals(OK, execute("--version"));
        assertTrue(out.toString().contains("FractaLens 1.1"), "The version should be displayed.");
    }

    /**
     * Test method for an unknown fractal name.
     */
    @Test
    void testUnknownFractalName() {
        int exitCode = execute("-f", "NOT_A_FRACTAL");
        assertEquals(USAGE_ERROR, exitCode);
        assertTrue(err.toString().contains("NOT_A_FRACTAL"), "The invalid value should be reported.");
    }

    /**
     * Test method for an unknown palette name.
     */
    @Test
    void testUnknownPaletteName() {
        assertEquals(USAGE_ERROR, execute("-p", "NOT_A_PALETTE"));
    }

    /**
     * Test method for a value that is not a number.
     */
    @Test
    void testInvalidNumber() {
        assertEquals(USAGE_ERROR, execute("-w", "abc"));
    }

    /**
     * Test method for an unknown option.
     */
    @Test
    void testUnknownOption() {
        assertEquals(USAGE_ERROR, execute("--not-an-option"));
    }

    /**
     * Test method for an output file that cannot be written.
     */
    @Test
    void testOutputCannotBeWritten() {
        Path file = tempDir.resolve("missing-directory").resolve("fractal.png");
        int exitCode = execute("-w", "10", "-h", "10", "-n", "10", "-o", file.toString());
        assertEquals(EXECUTION_ERROR, exitCode);
    }

}