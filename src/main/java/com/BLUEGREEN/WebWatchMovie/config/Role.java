package com.BLUEGREEN.WebWatchMovie.config;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
//    ADMIN(1), // Vai trò quản trị viên, có quyền cao nhất trong hệ thống.
//    USER(2); // Vai trò người dùng bình thường, có quyền hạn giới hạn.

    isView(1),
    isManager(2),
    isEdit(3),
    isAdd(4),
    isDelete(5),
    isPartner(6),
    isMaster(7);

    public final long value; // Biến này lưu giá trị số tương ứng với mỗi vai trò.
}