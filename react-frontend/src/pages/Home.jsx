import axios from "axios"
import { useEffect } from "react"
import { useState } from "react"
import { Link } from "react-router-dom"

export default function Home() {
    const [users, setUsers] = useState([])

    useEffect(() => {
        loadUsers()
    }, [])

    const loadUsers = async () => {
        const result = await axios.get("http://localhost:8080/users")
        setUsers(result.data)
    }

    const deleteUser = (id) => async () => {
        await axios.delete(`http://localhost:8080/user/${id}`)
        loadUsers()
    }

    return (
        <div className="container">
            <div className="py-4">
                <table className="table table-bordered border">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Username</th>
                            <th scope="col">Email</th>
                            <th scope="col">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            users.map(user => {
                                return (
                                    <tr key={user.id}>
                                        <th scope="row">{user.id}</th>
                                        <td>{user.name}</td>
                                        <td>{user.username}</td>
                                        <td>{user.email}</td>
                                        <td>
                                            <Link to={`/viewuser/${user.id}`} className="btn btn-primary mx-2">View</Link>
                                            <Link to={`/edituser/${user.id}`} className="btn btn-outline-primary mx-2">Edit</Link>
                                            <button className="btn btn-danger mx-2" onClick={deleteUser(user.id)}>Delete</button>
                                        </td>
                                    </tr>
                                )
                            })
                        }
                    </tbody>
                </table>
            </div>
        </div>
    )
}
