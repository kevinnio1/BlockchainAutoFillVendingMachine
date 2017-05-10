/**
 * Created by Yves on 2/19/2017.
 */
export interface User {
  username: string;
  password: string;
  walletID: string;
}

export interface PasswordChange {
  currentPassword: string;
  newPassword: string;
}
