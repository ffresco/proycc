/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.service.currentuser;

import com.proycc.base.domain.CurrentUser;

/**
 *
 * @author fafre
 */
public interface CurrentUserService {
     boolean canAccessUser(CurrentUser currentUser, Long userId);
}
