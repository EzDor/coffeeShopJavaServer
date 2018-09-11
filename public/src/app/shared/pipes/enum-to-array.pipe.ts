import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'enumToArray'
})
export class EnumToArrayPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    const keys = Object.keys(value);
    return keys.slice(keys.length / 2);
  }

}
