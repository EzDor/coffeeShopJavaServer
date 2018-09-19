import {Component, OnInit} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {Constants} from '@models/constants';
import {AdminTabs} from '@models/admin/admin-tabs.enum';
import {AdminService} from '@services/admin.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  public adminTabs: MenuItem[];
  public activeItem: MenuItem;
  public defaultTab: AdminTabs;

  constructor(private adminService: AdminService) {
  }

  ngOnInit() {
    this.defaultTab = this.adminService.defaultTab;
    this.adminTabs = [
      {
        label: Constants.ADMIN_TAB_PRODUCT_LABEL,
        icon: Constants.ADMIN_TAB_PRODUCT_ICON,
        command: () => this.updateCurrentTab(AdminTabs.product)
      },
      {
        label: Constants.ADMIN_TAB_COMPONENT_LABEL,
        icon: Constants.ADMIN_TAB_COMPONENT_ICON,
        command: () => this.updateCurrentTab(AdminTabs.component)
      },
      {
        label: Constants.ADMIN_TAB_USERS_LABEL,
        icon: Constants.ADMIN_TAB_USERS_ICON,
        command: () => this.updateCurrentTab(AdminTabs.user)
      },
    ];

    this.activeItem = this.adminTabs[this.defaultTab];
  }

  public updateCurrentTab(tab: AdminTabs) {
    this.adminService.updateTab(tab);
  }

}
