import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';

/**
 * 利用者の業務処理コンポーネントクラス。
 */
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

    constructor(private userService: UserService) { }

    //-----------------------------
    // コンポーネント内プロパティ
    //-----------------------------

    ngOnInit() {
    }

    public signin() {
        
    }

}
