package third_task;

import com.tpo.third_task.Entity;
import com.tpo.third_task.Identity;
import com.tpo.third_task.Position;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ThinkingTest {

    private Entity actualEntity;

    @BeforeEach
    public void init() {
        actualEntity = new Entity(Position.STAYING, Identity.NOT_DETERMINED, true);
    }

    @Test
    @DisplayName("common thinking test")
    public void commonThinkingTest() throws InterruptedException {
        actualEntity.startIdentify(Identity.WHALE);
        Thread.sleep(5000);
        Entity expectedEntity = new Entity(Position.STAYING, Identity.WHALE, true);
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    @DisplayName("text test")
    public void textTest() throws InterruptedException {
        actualEntity.setPosition(Position.CURLED);
        actualEntity.startIdentify(Identity.WHALE);
        for (Thread thread : actualEntity.getThinks()) {
            thread.join();
        }
        actualEntity.startIdentify(Identity.CAMERA);
        for (Thread thread : actualEntity.getThinks()) {
            thread.join();
        }
        Entity expectedEntity = new Entity(Position.CURLED, Identity.CAMERA, true);
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    @DisplayName("common kill test")
    public void CommonKillTest() {
        actualEntity.startIdentify(Identity.CAMERA);
        actualEntity.startIdentify(Identity.WHALE);
        actualEntity.startIdentify(Identity.NOT_DETERMINED);
        actualEntity.startIdentify(Identity.CHAIR);
        actualEntity.die();
        Entity expectedEntity = new Entity(Position.LYING, Identity.NOT_DETERMINED, false);
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    @DisplayName("kill on empty test")
    public void emptyKillTest() {
        actualEntity.die();
        Entity expectedEntity = new Entity(Position.LYING, Identity.NOT_DETERMINED, false);
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    @DisplayName("double kill :)")
    public void doubleKillTest() {
        actualEntity.die();
        assertThrows(IllegalStateException.class, () -> actualEntity.die());
    }

    @SneakyThrows
    @Test
    @DisplayName("interrupt think")
    public void interruptThink() {
        actualEntity.startIdentify(Identity.CAMERA);
        Thread.sleep(1000);
        for (Thread t : actualEntity.getThinks()) {
            t.interrupt();
        }
        Entity expectedEntity = new Entity(Position.STAYING, Identity.NOT_DETERMINED, true);
        assertEquals(expectedEntity, actualEntity);
    }
}
