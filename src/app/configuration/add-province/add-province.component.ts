import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-province',
  standalone: true,
  templateUrl: './add-province.component.html',
  styleUrls: ['./add-province.component.css'],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ]
})
export class AddProvinceComponent {
  @Output() formSubmitted = new EventEmitter<any>();

  provinceForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AddProvinceComponent>
  ) {
    this.provinceForm = this.fb.group({
      name: ['', Validators.required]
    });
  }

  submit() {
    if (this.provinceForm.valid) {
      this.formSubmitted.emit(this.provinceForm.value);
      this.dialogRef.close(this.provinceForm.value);
    }
  }

  close() {
    this.dialogRef.close();
  }
}
