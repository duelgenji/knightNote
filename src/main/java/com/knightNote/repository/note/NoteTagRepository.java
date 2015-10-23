package com.knightNote.repository.note;

import com.knightNote.entity.note.NoteTag;
import com.wonders.xlab.framework.repository.MyRepository;

/**
 * Created by knightNote on 15/10/16.
 */
public interface NoteTagRepository extends MyRepository<NoteTag, Long> {

    NoteTag findByContent(String content);

}
