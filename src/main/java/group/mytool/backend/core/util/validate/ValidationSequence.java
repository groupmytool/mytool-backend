package group.mytool.backend.core.util.validate;

import jakarta.validation.GroupSequence;

@GroupSequence({Order1.class, Order2.class, Order3.class, Order4.class, Order5.class, Order6.class})
public interface ValidationSequence {
}
