import React, { useState } from 'react'
import { Stack, Button, TextField } from '@mui/material'
import SendIcon from '@mui/icons-material/Send';
import './css/Register.css';
import RestService from '../services/RestService';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { useForm } from "react-hook-form";
import LoginLogo from '../images/bg1.jpg';
import InputLabel from '@mui/material/InputLabel';
import InputAdornment from '@mui/material/InputAdornment';
import OutlinedInput from '@mui/material/OutlinedInput';
import IconButton from '@mui/material/IconButton';
import FormControl from '@mui/material/FormControl';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';

function RegisterValidation() {
    const navigate = useNavigate();
    const [errorMsg, setErrorMsg] = useState('');

    const { register, handleSubmit, formState: { errors } } = useForm({
        defaultValues: {
            firstName: "",
            lastName: "",
            userName: "",
            password: "",
            emailId: "",
            mobileNumber: ""
        }
    });

    const [values, setValues] = useState({
        showPassword: false
    })
    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };

    const handleClickShowPassword = () => {
        setValues({
            ...values,
            showPassword: !values.showPassword,
        });
    };

    const onSubmit = (data) => {
        console.log(data);
        RestService.registerUser(data)
            .then((res) => {
                if (res.status === 201) {
                    alert('User Registered Successfully');
                    navigate('/');
                }
            })
            .catch((err) => {
                console.log(err);
                setErrorMsg('Username Already Exists');
            });
    }


    return (
        <section className="register py-5 bg-light">
            <div className="container">

                <div className="row g-0 register-row">

                    <div className="col-lg-5">
                        <img src={LoginLogo} className="img-fluid" alt="login" />
                    </div>
                    <form className="col-lg-7 text-center py-3 px-5" onSubmit={handleSubmit(onSubmit)}>
                        <h2>Create Your Account</h2>
                        <Stack display='block' spacing={4} >
                            <Stack spacing={2} direction='row'>
                                <TextField label='First Name' size='small' {...register("firstName", {
                                    required: {
                                        value: true,
                                        message: "First Name is Required"
                                    },
                                    pattern: {
                                        value: /[a-zA-Z]+([\s][a-zA-Z]+)*/,
                                        message: "Invalid First Name"
                                    }
                                })}
                                error={Boolean(errors.firstName)} helperText={errors.firstName?.message}/>
                                <TextField label='Last Name' size='small' {...register("lastName", {
                                    required: {
                                        value: true,
                                        message: "Last Name is Required"
                                    }, pattern: {
                                        value: /[a-zA-Z]+([\s][a-zA-Z]+)*/,
                                        message: "Invalid Last Name"
                                    }
                                })}
                                    error={Boolean(errors.lastName)} helperText={errors.lastName?.message} />
                            </Stack>

                            <Stack spacing={2} direction='row'>
                                <TextField label='User Name' size='small' {...register("userName", {
                                    required: {
                                        value: true,
                                        message: "User Name is Required"
                                    },
                                    pattern: {
                                        value: /[a-zA-Z]+([\s][a-zA-Z]+)*/,
                                        message: "Invalid User Name"
                                    }
                                })}
                                    error={Boolean(errors.userName)} helperText={errors.userName?.message} />
                                <p className="error">{errorMsg}</p>
                            </Stack>
                            <Stack spacing={2} direction='row'>
                                <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
                                    <InputLabel htmlFor="outlined-adornment-password">Password</InputLabel>
                                    <OutlinedInput
                                        id="outlined-adornment-password"
                                        type={values.showPassword ? 'text' : 'password'}
                                        value={values.password}

                                        endAdornment={
                                            <InputAdornment position="end">
                                                <IconButton
                                                    aria-label="toggle password visibility"
                                                    onClick={handleClickShowPassword}
                                                    onMouseDown={handleMouseDownPassword}
                                                    edge="end"
                                                >
                                                    {values.showPassword ? <VisibilityOff /> : <Visibility />}
                                                </IconButton>
                                            </InputAdornment>
                                        }
                                        label="Password"
                                        {...register("password", {
                                            required: {
                                                value: true,
                                                message: "Password is Required"
                                            },
                                            pattern: {
                                                value: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/,
                                                message: "Password should be Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character"
                                            }
                                        })}
                                        error={Boolean(errors.password)} helperText={errors.password?.message}
                                    />
                                </FormControl>
                            </Stack>
                            <Stack spacing={2} direction='row'>
                                <TextField label='Email' size='small' type="email" {...register("emailId", {
                                    required: {
                                        value: true,
                                        message: "Email Id is Required"
                                    },
                                    pattern: {
                                        value: /\S+@\S+\.\S+/,
                                        message: "Invalid Email Id"
                                    }
                                })}
                                    error={Boolean(errors.emailId)} helperText={errors.emailId?.message} />
                            </Stack>
                            <Stack spacing={2} direction='row'>
                                <TextField label='Mobile Number' size='small' type="number" {...register("mobileNumber", {
                                    required: {
                                        value: true,
                                        message: "Mobile Number is Required"
                                    },
                                    minLength: {
                                        value: 10,
                                        message: "Mobile Number should be 10 digits"
                                    },
                                    maxLength: {
                                        value: 10,
                                        message: "Mobile Number should be 10 digits"
                                    }
                                })}
                                    error={Boolean(errors.mobileNumber)} helperText={errors.mobileNumber?.message} />
                            </Stack>
                            <Stack spacing={2} direction='row'>
                                <Button type="submit" variant='contained' color='warning' startIcon={<SendIcon />}>Register</Button>
                                <Link to={"/"} className="btn btn-outline-danger">Cancel</Link>
                            </Stack>
                        </Stack>
                    </form>
                </div>
            </div>

        </section>
    )
}

export default RegisterValidation
