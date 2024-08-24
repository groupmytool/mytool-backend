package group.mytool.flutter.flex.backend.common.user.service;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import group.mytool.flutter.flex.backend.common.user.entity.po.SessionRecord;
import group.mytool.flutter.flex.backend.common.user.mapper.SessionRecordMapper;
import org.springframework.stereotype.Service;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Service
public class SessionRecordService extends ServiceImpl<SessionRecordMapper, SessionRecord> {
}
