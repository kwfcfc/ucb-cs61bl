package wordnet;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

class WordGraphTest {

    @Test
    void searchHyponyms() {
        WordGraph wn = new WordGraph("./data/wordnet/hyponyms11.txt", "./data/wordnet/synsets11.txt");
        assertThat(wn.searchHyponyms("antihistamine")).isEqualTo(Set.of("antihistamine", "actifed"));
    }
}
