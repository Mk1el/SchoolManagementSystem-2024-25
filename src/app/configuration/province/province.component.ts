import { Component, OnInit } from '@angular/core';
import { ConfigureSchoolsService } from '../configure-schools.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { AddProvinceComponent } from '../add-province/add-province.component';
import { Province } from '../../models/geography.model';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-province-table',
  standalone: true,
  imports:[FormsModule,MatTableModule, MatFormFieldModule, MatInputModule, MatIconModule, MatDialogModule,],
  templateUrl: './province.component.html',
  styleUrls: ['./province.component.css']
})
export class ProvinceTableComponent implements OnInit {
  data: Province[] = [];
  filterText = '';
  cols = ['id', 'name', 'actions'];

  constructor(
    private svc: ConfigureSchoolsService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.refresh();
  }

  refresh() {
    
  }

  get filteredData() {
    const term = this.filterText.toLowerCase();
    return this.data.filter(p => p.name.toLowerCase().includes(term));
  }

  openAddDialog() {
    const dlg = this.dialog.open(AddProvinceComponent, {
      width: '400px',
      data: { name: '' }
    });

    dlg.afterClosed().subscribe(result => {
      if (result) {
        this.svc.addProvince({ name: result }).subscribe(() => this.refresh());
      }
    });
  }
}
