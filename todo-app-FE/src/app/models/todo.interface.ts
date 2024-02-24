import { User } from "./user.interface";

export interface Todo{
id:string,
description: string,
priority: number,
deadline: Date,
completed:boolean,
created_at:Date,
user:User
}
