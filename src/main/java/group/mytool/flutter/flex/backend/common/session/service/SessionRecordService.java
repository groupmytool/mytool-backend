package group.mytool.flutter.flex.backend.common.session.service;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import group.mytool.flutter.flex.backend.common.session.entity.SessionRecord;
import group.mytool.flutter.flex.backend.common.session.mapper.SessionRecordMapper;
import org.springframework.stereotype.Service;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Service
public class SessionRecordService extends ServiceImpl<SessionRecordMapper, SessionRecord> {
}
