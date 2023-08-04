import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  constructor() { }

  upload(file: File) {
    // For this example, we'll just log the name of the uploaded file.
    // In a real-world application, you'd send the file to your backend here.
    console.log(`Uploaded file: ${file.name}`);
  }
}
