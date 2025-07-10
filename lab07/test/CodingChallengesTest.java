import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class CodingChallengesTest {

    @Test
    public void testMissingNumber() {
        int values[] = {3, 5, 1, 4, 0};
        assertThat(CodingChallenges.missingNumber(values)).isEqualTo(2);
    }

    @Test
    public void testIsPermutation() {
        String s1 = "barcelona";
        String s2 = "ecornlbaa";
        String moreString = "barcelonasss";
        String lessString = "brclon";
        String emptyString = "";
        String nullString = null;

        assertThat(CodingChallenges.isPermutation(s1, s2)).isTrue();
        assertThat(CodingChallenges.isPermutation(s2, moreString)).isFalse();
        assertThat(CodingChallenges.isPermutation(lessString, s1)).isFalse();
        assertThat(CodingChallenges.isPermutation(emptyString, s1)).isFalse();
        assertThat(CodingChallenges.isPermutation(nullString, s1)).isFalse();
    }
}

