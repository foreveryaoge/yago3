package fromOtherSources;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import javatools.administrative.Announce;
import javatools.datatypes.FinalSet;
import basics.Fact;
import basics.FactSource;
import basics.Theme;
import extractors.DataExtractor;

/**
 * HardExtractor - YAGO2s
 * 
 * Produces the hard-coded facts.
 * 
 * @author Fabian
 * 
 */
public class HardExtractor extends DataExtractor {

	/** Our output */
	public static final Theme HARDWIREDFACTS = new Theme("hardWiredFacts",
			"The manually created facts of YAGO");

	public Set<Theme> output() {
		return (new FinalSet<Theme>(HARDWIREDFACTS));
	}

	@Override
	public void extract() throws Exception {
		Announce.doing("Copying hard wired facts");
		Announce.message("Input folder is", inputData);
		if (!inputData.equals(new File("../basics2s/data")))
			Announce.warning(
					"The Hardextractor should be run on '../basics2s/data', not on",
					inputData);
		for (File f : inputData.listFiles()) {
			Announce.doing("Copying hard wired facts from", f.getName());
			for (Fact fact : FactSource.from(f)) {
				HARDWIREDFACTS.write(fact);
			}
			Announce.done();
		}
		Announce.done();
	}

	public HardExtractor(File inputFolder) {
		super(inputFolder);
		if (!inputFolder.exists())
			throw new RuntimeException("Folder not found " + inputFolder);
		if (!inputFolder.isDirectory())
			throw new RuntimeException("Not a folder: " + inputFolder);
	}

	@Override
	public Set<Theme> input() {
		return new TreeSet<Theme>();
	}

	public static void main(String[] args) throws Exception {
		new HardExtractor(new File("../basics2s/data")).extract(new File(
				"c:/fabian/data/yago3"), "test");
	}
}
