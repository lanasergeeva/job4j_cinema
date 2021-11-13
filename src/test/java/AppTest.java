
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AppTest {
    @Test
    public void whenSum() {
        App app = new App();
        assertThat(app.sum(5), is(10));
    }
}