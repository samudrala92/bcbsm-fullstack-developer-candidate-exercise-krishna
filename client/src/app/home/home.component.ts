import { Component } from '@angular/core';
import { FileUploadService } from '../file-upload.service';
import { DataService } from '../data.service';
import * as XLSX from 'xlsx';
import { MatTableDataSource } from '@angular/material/table';
import { ChangeDetectorRef } from '@angular/core';


// let jsonData = XLSX.utils.sheet_to_json(worksheet, {raw: true}).map((data: any) => {
//   return {
//     column1: data['column1'],
//     column2: data['column2'],
//     column3: data['column3'],
//     column4: data['column4'],
//     column5: data['column5'],
//   }
// }) as TableData[];

export interface TableData {
  documentID: string;
  documentName: string;
  document: string;
  uploadedUser: string;
  uploadedTime: string;
}


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  selectedFile: File | null = null;
  displayedColumns: string[] = ['documentID', 'documentName', 'document', 'uploadedUser', 'uploadedTime'];
  dataSource: MatTableDataSource<TableData> = new MatTableDataSource<TableData>();
  jsonData: string[] = [];


  constructor(private fileUploadService: FileUploadService, private changeDetectorRef: ChangeDetectorRef, private dataService: DataService) { }

 onFileSelected(event: Event) {
     const element = event.target as HTMLInputElement;
     if (element.files) {
         this.selectedFile = <File>element.files[0];
     }
 }

  onUpload() {
  console.log(this.selectedFile);

    if (this.selectedFile) {
          let fileReader = new FileReader();
          fileReader.onload = (e) => {
            let arrayBuffer: any = fileReader.result;
            let data = new Uint8Array(arrayBuffer);
            let arr = new Array();
            for(let i = 0; i != data.length; ++i) arr[i] = String.fromCharCode(data[i]);
            let bstr = arr.join("");

            let workbook = XLSX.read(bstr, {type:"binary"});

            let firstSheetName = workbook.SheetNames[0];
            let worksheet = workbook.Sheets[firstSheetName];

            let jsonData = XLSX.utils.sheet_to_json(worksheet,{raw:true});
            console.log(jsonData);

            this.dataSource = new MatTableDataSource<TableData>(jsonData as TableData[]);
//             this.dataSource = new MatTableDataSource(jsonData);
            console.log(this.dataSource);
            this.changeDetectorRef.detectChanges();
            this.sendDataToApi();
          }
          fileReader.readAsArrayBuffer(this.selectedFile);
    }

 }


  applyFilter(filterValue: string) {
    filterValue = filterValue.trim().toLowerCase();
    this.dataSource.filter = filterValue;
    this.detectChanges();
  }

   private detectChanges() {
      this.changeDetectorRef.detectChanges();
    }

    sendDataToApi() {
        this.dataService.sendData(this.jsonData)
          .subscribe(
            response => {
              console.log('Data sent to API successfully!', response);
               this.detectChanges();
            },
            error => {
              console.error('Error sending data to API:', error);
            }
          );
      }
}
