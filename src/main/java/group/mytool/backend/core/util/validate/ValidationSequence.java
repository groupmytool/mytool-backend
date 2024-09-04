package group.mytool.backend.core.util.validate;

import jakarta.validation.GroupSequence;

@GroupSequence({OrderFirst.class, OrderSecond.class, OrderThird.class, OrderFourth.class, OrderFifth.class})
public interface ValidationSequence {
}
